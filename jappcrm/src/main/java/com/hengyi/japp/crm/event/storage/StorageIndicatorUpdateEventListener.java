package com.hengyi.japp.crm.event.storage;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.event.SyncEventPublisher;
import com.hengyi.japp.crm.event.indicator.IndicatorUpdateEvent;

@Named
@Transactional
public class StorageIndicatorUpdateEventListener implements
		ApplicationListener<StorageIndicatorUpdateEvent> {
	@Inject
	protected SyncEventPublisher syncEventPublisher;

	@Override
	public void onApplicationEvent(StorageIndicatorUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		syncEventPublisher.publish(new IndicatorUpdateEvent(nodeId));
	}
}
