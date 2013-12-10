package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.event.CertificateUpdateEvent;

@Named
@Scope("view")
public class CertificateManager extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -8268911897849884449L;
	private Long nodeId;
	private CertificateDTO certificate;

	public void save() {
		try {
			syncEventPublisher.publish(new CertificateUpdateEvent(
					getCertificate(), getCurrentOperatorNodeId()));
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public CertificateDTO getCertificate() {
		return certificate;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setCertificate(CertificateDTO certificate) {
		this.certificate = certificate;
	}
}
