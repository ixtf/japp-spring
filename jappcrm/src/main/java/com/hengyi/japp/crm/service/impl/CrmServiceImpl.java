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
import com.google.common.collect.Sets;
import com.hengyi.japp.crm.domain.Associate;
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
import com.hengyi.japp.crm.service.CacheServiceFacade;
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
	private CacheServiceFacade cacheServiceFacade;

	@Override
	public Crm findOne(Long nodeId) {
		return crmRepository.findOne(nodeId);
	}

	@Override
	public void save(Crm crm,
			Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			CrmType crmType, Communicatee communicatee,
			Iterable<Communicatee> communicatees, Iterable<Associate> associates) {
		crm.setCrmType(crmType);
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
