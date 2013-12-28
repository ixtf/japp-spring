package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

public class CrmFieldDeleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = -7015127047927386858L;
	private final Long operatorNodeId;

	public CrmFieldDeleteEvent(Long operatorNodeId, Long crmFieldNodeId) {
		super(crmFieldNodeId);
		this.operatorNodeId = operatorNodeId;
	}

	public Long getCrmFieldNodeId() {
		return (Long) getSource();
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}
}
