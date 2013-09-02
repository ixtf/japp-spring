package com.hengyi.japp.crm.event;

import com.google.common.base.Objects;

public class CrmUpdateEvent {
	private Long nodeId;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public CrmUpdateEvent(Long nodeId) {
		super();
		this.nodeId = nodeId;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getNodeId()).toString();
	}
}
