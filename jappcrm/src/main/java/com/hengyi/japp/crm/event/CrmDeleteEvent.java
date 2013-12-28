package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

public class CrmDeleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = -7015127047927386858L;
	private final Long operatorNodeId;

	public CrmDeleteEvent(Long operatorNodeId, Long crmNodeId) {
		super(crmNodeId);
		this.operatorNodeId = operatorNodeId;
	}

	public Long getCrmNodeId() {
		return (Long) getSource();
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}
}
