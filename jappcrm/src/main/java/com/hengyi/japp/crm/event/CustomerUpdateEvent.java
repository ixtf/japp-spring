package com.hengyi.japp.crm.event;

public class CustomerUpdateEvent {
	private Long nodeId;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public CustomerUpdateEvent(Long nodeId) {
		super();
		this.nodeId = nodeId;
	}
}
