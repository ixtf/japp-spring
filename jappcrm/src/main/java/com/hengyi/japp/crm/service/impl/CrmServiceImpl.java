package com.hengyi.japp.crm.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
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
import com.hengyi.japp.crm.exception.NoChiefCommunicateeException;
import com.hengyi.japp.crm.exception.NoIndicatorValueException;
import com.hengyi.japp.crm.service.CacheService;
import com.hengyi.japp.crm.service.CrmService;

public abstract class CrmServiceImpl<T extends Crm> extends
		CommonUrlServiceImpl<Long> implements CrmService<T> {
	@Resource
	private Neo4jOperations template;
	@Resource
	private CrmRepository crmRepository;

	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected SyncEventPublisher syncEventPublisher;
	@Inject
	protected CacheService cacheServiceFacade;

	// @Inject
	// private EventBus eventBus;

	public void save(T crm,
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
	}

	private void checkSave(T crm,
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

	public void delete(T crm) throws Exception {
		crmRepository.delete(crm);
	}

	// 取Crm已经选中的indicatorValue，在和indicator关联的indicatorValue中选
	public Map<Indicator, List<IndicatorValueScore>> getIndicatorMap(Crm crm,
			Iterable<Indicator> indicators) {
		ImmutableMap.Builder<Indicator, List<IndicatorValueScore>> builder = ImmutableMap
				.builder();
		Set<IndicatorValue> indicatorValues = ImmutableSet.copyOf(crm
				.getIndicatorValues(template));
		for (Indicator indicator : indicators)
			builder.put(indicator,
					getIndicatorValueScores(indicator, indicatorValues));
		return builder.build();
	}

	private List<IndicatorValueScore> getIndicatorValueScores(
			Indicator indicator, Set<IndicatorValue> indicatorValues) {
		List<IndicatorValueScore> result = Lists.newArrayList();
		for (IndicatorValueScore indicatorValueScore : indicator
				.getIndicatorValueScores(template))
			if (indicatorValues.contains(indicatorValueScore.getEnd()))
				result.add(indicatorValueScore);
		return result;
	}

	// @PostConstruct
	// private void init() {
	// eventBus.register(this);
	// cacheServiceFacade.getAsyncEventBus().register(this);
	// }
}
