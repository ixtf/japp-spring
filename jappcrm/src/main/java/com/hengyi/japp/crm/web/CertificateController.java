package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Certificate;

@Named
@Scope("view")
public class CertificateController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -8268911897849884449L;
	private Long nodeId;
	private Certificate certificate;

	public void save() {
		try {
			getCertificate().setOperator(getCurrentOperator());
			certificateService.save(getCertificate());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public Certificate getCertificate() {
		if (certificate != null)
			return certificate;
		if (nodeId == null)
			certificate = new Certificate();
		else
			certificate = certificateService.findOne(nodeId);
		return certificate;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}
}
