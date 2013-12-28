package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.Url;
import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.event.CertificateDeleteEvent;

@Named
@Scope("view")
public class CertificatesController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<CertificateDTO> certificates;
	private CertificateDTO certificate;

	public List<CertificateDTO> getCertificates() {
		return certificates;
	}

	public CertificateDTO getCertificate() {
		return certificate;
	}

	public void setCertificate(CertificateDTO certificate) {
		this.certificate = certificate;
	}

	public void edit() {
		redirect(Url.certificate.getUpdatePath(getCertificate().getNodeId()));
	}

	public void delete() {
		try {
			eventPublisher.publish(new CertificateDeleteEvent(
					getCurrentOperator().getNodeId(), getCertificate()
							.getNodeId()));
			certificates.remove(certificate);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	@PostConstruct
	public void init() {
		certificates = queryService.findAllCertificate();
	}
}
