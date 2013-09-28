package com.hengyi.japp.crm.event.storage;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.domain.storage.StorageIndicator;

public class StorageIndicatorUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3942193835269289143L;

	public StorageIndicatorUpdateEvent(StorageIndicator customerIndicator) {
		super(customerIndicator.getNodeId());
	}

	public StorageIndicatorUpdateEvent(Long nodeId) {
		super(nodeId);
	}
}
