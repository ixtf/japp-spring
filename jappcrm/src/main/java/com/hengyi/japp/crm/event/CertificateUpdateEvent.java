package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.dto.CertificateDTO;

public class CertificateUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = 7820361085553280539L;
	private final Long operatorNodeId;

	public CertificateUpdateEvent(Long operatorNodeId,
			CertificateDTO certificate) {
		super(certificate);
		this.operatorNodeId = operatorNodeId;
	}

	public CertificateDTO getCertificate() {
		return (CertificateDTO) getSource();
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}
}
