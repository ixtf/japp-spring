package com.hengyi.japp.crm.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorScore;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.event.CrmUpdateEvent;
import com.hengyi.japp.crm.service.CrmService;

@Named
@Transactional
public class CrmServiceImpl implements CrmService {
	@Inject
	protected Neo4jOperations template;
	@Inject
	private CrmRepository crmRepository;
	@Inject
	private EventBus eventBus;

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
		Set<IndicatorScore> indicatorScores = Sets.newHashSet();
		Set<IndicatorValue> indicatorValues = Sets.newHashSet();
		for (Entry<Indicator, List<IndicatorValueScore>> entry : indicatorMap
				.entrySet()) {
			Indicator indicator = entry.getKey();
			IndicatorScore indicatorScore = new IndicatorScore(crm, indicator);
			indicatorScores.add(indicatorScore);
			double maxScore = 0;
			for (IndicatorValueScore indicatorValueScore : entry.getValue()) {
				indicatorValues.add(indicatorValueScore.getEnd());
				double score = indicatorValueScore.getScore();
				maxScore = maxScore < score ? score : maxScore;
			}
			indicatorScore.setScore(maxScore * indicator.getPercent());
		}
		crm.setIndicatorScores(indicatorScores);
		crm.setIndicatorValues(indicatorValues);
		crmRepository.save(crm);
		for (Associate associate : associates)
			template.save(associate);
		for (IndicatorScore indicatorScore : indicatorScores)
			template.save(indicatorScore);
		// TODO test--delete
		eventBus.post(new CrmUpdateEvent(crm.getNodeId()));
	}

	@Override
	public void delete(Crm crm) throws Exception {
		crmRepository.delete(crm);
	}

	@Override
	public Map<Indicator, List<IndicatorValueScore>> getIndicatorMap(Crm crm) {
		// TODO Auto-generated method stub
		ImmutableMap.Builder<Indicator, List<IndicatorValueScore>> builder = ImmutableMap
				.builder();
		return builder.build();
	}

	@Subscribe
	public void crmUpdateEvent(CrmUpdateEvent event) {
		System.out.print(event);
	}

	@PostConstruct
	private void init() {
		eventBus.register(this);
	}
}
