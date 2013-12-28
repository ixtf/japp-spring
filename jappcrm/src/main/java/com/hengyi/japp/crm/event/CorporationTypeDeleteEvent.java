package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

public class CorporationTypeDeleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = 8682579594542202538L;
	private final Long operatorNodeId;

	public CorporationTypeDeleteEvent(Long operatorNodeId,
			Long corporationTypeNodeId) {
		super(corporationTypeNodeId);
		this.operatorNodeId = operatorNodeId;
	}

	public Long getCorporationTypeNodeId() {
		return (Long) getSource();
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}
}
