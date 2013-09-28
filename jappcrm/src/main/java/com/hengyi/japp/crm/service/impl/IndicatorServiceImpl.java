package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.repository.IndicatorRepository;
import com.hengyi.japp.crm.event.EventPublisher;
import com.hengyi.japp.crm.event.SyncEventPublisher;
import com.hengyi.japp.crm.event.indicator.IndicatorUpdateEvent;
import com.hengyi.japp.crm.service.IndicatorService;

@Named
@Transactional
public class IndicatorServiceImpl implements IndicatorService {
	@Resource
	protected Neo4jOperations template;
	@Resource
	private IndicatorRepository indicatorRepository;
	@Inject
	private EventPublisher eventPublisher;
	@Inject
	private SyncEventPublisher syncEventPublisher;

	@Override
	public Indicator findOne(Long nodeId) {
		return indicatorRepository.findOne(nodeId);
	}

	public void save(Indicator indicator,
			Iterable<IndicatorValueScore> indicatorValueScores)
			throws Exception {
		indicator.setIndicatorValueScores(indicatorValueScores);
		indicatorRepository.save(indicator);
		for (IndicatorValueScore indicatorValueScore : indicatorValueScores)
			template.save(indicatorValueScore);
		syncEventPublisher.publish(new IndicatorUpdateEvent(indicator));
	}

	@Override
	public void delete(Indicator indicator) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Indicator> findAll() {
		return Lists.newArrayList(indicatorRepository.findAll());
	}
}
