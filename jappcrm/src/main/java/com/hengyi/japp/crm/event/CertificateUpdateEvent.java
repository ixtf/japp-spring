package com.hengyi.japp.crm.event;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.application.AbstractApplicationEvent;
import com.hengyi.japp.crm.dto.CertificateDTO;

@NodeEntity
public class CertificateUpdateEvent extends AbstractApplicationEvent {
	public CertificateUpdateEvent(Object source, Long operatorNodeId) {
		super(source, operatorNodeId);
	}

	public CertificateDTO getCertificate() {
		return (CertificateDTO) getSource();
	}
}
