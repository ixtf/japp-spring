package com.hengyi.japp.crm.event.storage;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.event.EventPublisher;
import com.hengyi.japp.crm.event.indicator.IndicatorUpdateEvent;

@Named
@Transactional
public class StorageIndicatorUpdateEventListener implements
		ApplicationListener<StorageIndicatorUpdateEvent> {
	@Inject
	protected EventPublisher eventPublisher;

	@Override
	public void onApplicationEvent(StorageIndicatorUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		eventPublisher.publish(new IndicatorUpdateEvent(nodeId));
	}
}
