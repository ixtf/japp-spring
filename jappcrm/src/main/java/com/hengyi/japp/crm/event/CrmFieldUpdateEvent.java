package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.dto.CrmFieldDTO;

public class CrmFieldUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = 7820361085553280539L;
	private final Long operatorNodeId;

	public CrmFieldUpdateEvent(Long operatorNodeId, CrmFieldDTO crmField) {
		super(crmField);
		this.operatorNodeId = operatorNodeId;
	}

	public CrmFieldDTO getCrmField() {
		return (CrmFieldDTO) getSource();
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}
}
