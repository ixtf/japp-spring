package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.dto.CorporationTypeDTO;

public class CorporationTypeUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3628635315812348036L;
	private final Long operatorNodeId;

	public CorporationTypeUpdateEvent(Long operatorNodeId,
			CorporationTypeDTO corporationType) {
		super(corporationType);
		this.operatorNodeId = operatorNodeId;
	}

	public CorporationTypeDTO getCorporationType() {
		return (CorporationTypeDTO) getSource();
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}
}
