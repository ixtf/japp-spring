package com.hengyi.japp.crm.event;

public class StorageUpdateEvent {
	private Long nodeId;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public StorageUpdateEvent(Long nodeId) {
		super();
		this.nodeId = nodeId;
	}
}
