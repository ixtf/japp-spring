package com.hengyi.japp.crm.event.indicator;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.repository.CrmRepository;

@Named
@Transactional
public class IndicatorUpdateEventListener implements
		ApplicationListener<IndicatorUpdateEvent> {
	@Resource
	private CrmRepository crmRepository;

	@Override
	public void onApplicationEvent(IndicatorUpdateEvent event) {
		Indicator indicator = (Indicator) event.getSource();
		for (Crm crm : crmRepository.findAllByIndicator(indicator))
			crm.indicatorScore(indicator);
	}
}
