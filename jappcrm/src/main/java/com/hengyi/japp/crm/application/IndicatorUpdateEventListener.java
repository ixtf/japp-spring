package com.hengyi.japp.crm.application;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.IndicatorRepository;
import com.hengyi.japp.crm.event.IndicatorUpdateEvent;

@Named
@Transactional
public class IndicatorUpdateEventListener implements
		ApplicationListener<IndicatorUpdateEvent> {
	@Resource
	private IndicatorRepository indicatorRepository;
	@Resource
	private CrmRepository crmRepository;
	@Inject
	private Neo4jTemplate template;

	@Override
	public void onApplicationEvent(IndicatorUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		Indicator indicator = indicatorRepository.findOne(nodeId);
		for (Crm crm : crmRepository.findAllByIndicator(indicator))
			crm.indicatorScore(template, indicator);
	}
}
