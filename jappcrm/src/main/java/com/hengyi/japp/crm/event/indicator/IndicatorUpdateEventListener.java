package com.hengyi.japp.crm.event.indicator;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.repository.CrmRepository;

@Named
@Transactional
public class IndicatorUpdateEventListener implements
		ApplicationListener<IndicatorUpdateEvent> {
	@Inject
	private Neo4jOperations template;
	@Inject
	private CrmRepository crmRepository;

	@Override
	public void onApplicationEvent(IndicatorUpdateEvent event) {
		Indicator indicator = (Indicator) event.getSource();
		for (Crm crm : crmRepository.findAllByIndicator(indicator))
			crm.indicatorScore(indicator, template);
	}
}
