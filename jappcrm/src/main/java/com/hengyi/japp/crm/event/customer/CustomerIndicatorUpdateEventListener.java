package com.hengyi.japp.crm.event.customer;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.event.EventPublisher;
import com.hengyi.japp.crm.event.indicator.IndicatorUpdateEvent;

@Named
@Transactional
public class CustomerIndicatorUpdateEventListener implements
		ApplicationListener<CustomerIndicatorUpdateEvent> {
	@Inject
	protected EventPublisher eventPublisher;

	@Override
	public void onApplicationEvent(CustomerIndicatorUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		eventPublisher.publish(new IndicatorUpdateEvent(nodeId));
	}
}
