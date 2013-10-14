package com.hengyi.japp.crm.event.customer;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.event.SyncEventPublisher;
import com.hengyi.japp.crm.event.indicator.IndicatorUpdateEvent;

@Named
@Transactional
public class CustomerIndicatorUpdateEventListener implements
		ApplicationListener<CustomerIndicatorUpdateEvent> {
	@Inject
	protected SyncEventPublisher syncEventPublisher;

	@Override
	public void onApplicationEvent(CustomerIndicatorUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		syncEventPublisher.publish(new IndicatorUpdateEvent(nodeId));
	}
}
