package com.hengyi.japp.crm.event.indicator;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.IndicatorRepository;

@Named
@Transactional
public class IndicatorUpdateEventListener implements
		ApplicationListener<IndicatorUpdateEvent> {
	@Resource
	private IndicatorRepository indicatorRepository;
	@Resource
	private CrmRepository crmRepository;

	@Override
	public void onApplicationEvent(IndicatorUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		Indicator indicator = indicatorRepository.findOne(nodeId);
		for (Crm crm : crmRepository.findAllByIndicator(indicator))
			crm.indicatorScore(indicator);
	}
}
