package com.hengyi.japp.crm.event;

public class IndicatorUpdateEvent {
	private Long nodeId;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public IndicatorUpdateEvent(Long nodeId) {
		super();
		this.nodeId = nodeId;
	}
}
