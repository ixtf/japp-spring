package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

public class CertificateDeleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = -7015127047927386858L;
	private final Long operatorNodeId;

	public CertificateDeleteEvent(Long operatorNodeId, Long certificateNodeId) {
		super(certificateNodeId);
		this.operatorNodeId = operatorNodeId;
	}

	public Long getCertificateNodeId() {
		return (Long) getSource();
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}
}
