package com.hengyi.japp.crm.event.storage;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.domain.storage.Storage;

public class StorageUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3942193835269289143L;

	public StorageUpdateEvent(Storage storage) {
		super(storage.getNodeId());
	}

	public StorageUpdateEvent(Long nodeId) {
		super(nodeId);
	}
}
