package com.hengyi.japp.crm.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.event.EventPublisher;
import com.hengyi.japp.crm.event.SyncEventPublisher;
import com.hengyi.japp.crm.event.crm.CrmUpdateEvent;
import com.hengyi.japp.crm.exception.NoChiefCommunicateeException;
import com.hengyi.japp.crm.exception.NoIndicatorValueException;
import com.hengyi.japp.crm.service.CacheService;
import com.hengyi.japp.crm.service.CrmService;

@Named
@Transactional
public class CrmServiceImpl implements CrmService {
	@Inject
	protected Neo4jOperations template;
	@Inject
	private CrmRepository crmRepository;
	@Inject
	private EventPublisher eventPublisher;
	@Inject
	private SyncEventPublisher syncEventPublisher;
	// @Inject
	// private EventBus eventBus;
	@Inject
	private CacheService cacheServiceFacade;

	@Override
	public Crm findOne(Long nodeId) {
		return crmRepository.findOne(nodeId);
	}

	private void checkSave(Crm crm,
			Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			CrmType crmType, Iterable<Certificate> certificates,
			Communicatee communicatee, Iterable<Communicatee> communicatees,
			Iterable<Associate> associates) throws Exception {
		if (communicatee == null)
			throw new NoChiefCommunicateeException();

		List<Indicator> noValueIndicators = Lists.newArrayList();
		for (Entry<Indicator, List<IndicatorValueScore>> entry : indicatorMap
				.entrySet())
			if (entry.getValue().isEmpty())
				noValueIndicators.add(entry.getKey());
		if (!noValueIndicators.isEmpty())
			throw new NoIndicatorValueException(noValueIndicators);
	}

	@Override
	public void save(Crm crm,
			Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			CrmType crmType, Iterable<Certificate> certificates,
			Communicatee communicatee, Iterable<Communicatee> communicatees,
			Iterable<Associate> associates) throws Exception {
		checkSave(crm, indicatorMap, crmType, certificates, communicatee,
				communicatees, associates);
		crm.setCrmType(crmType);
		crm.setCertificates(certificates);
		crm.setCommunicatee(communicatee);
		crm.setCommunicatees(communicatees);
		crm.setAssociates(associates);
		Set<IndicatorValue> indicatorValues = Sets.newHashSet();
		for (Entry<Indicator, List<IndicatorValueScore>> entry : indicatorMap
				.entrySet())
			for (IndicatorValueScore indicatorValueScore : entry.getValue())
				indicatorValues.add(indicatorValueScore.getEnd());
		crm.setIndicatorValues(indicatorValues);
		crmRepository.save(crm);
		for (Associate associate : associates)
			template.save(associate);
		eventPublisher.publish(new CrmUpdateEvent(crm));
	}

	@Override
	public void delete(Crm crm) throws Exception {
		crmRepository.delete(crm);
	}

	@Override
	public Map<Indicator, List<IndicatorValueScore>> getIndicatorMap(Crm crm,
			Iterable<Indicator> indicators) {
		ImmutableMap.Builder<Indicator, List<IndicatorValueScore>> builder = ImmutableMap
				.builder();
		for (Indicator indicator : indicators)
			builder.put(indicator,
					indicator.getIndicatorValueScores(crm, template));
		return builder.build();
	}

	// @PostConstruct
	// private void init() {
	// eventBus.register(this);
	// cacheServiceFacade.getAsyncEventBus().register(this);
	// }
}
