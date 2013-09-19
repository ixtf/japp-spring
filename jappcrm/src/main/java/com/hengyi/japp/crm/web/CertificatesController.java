package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Certificate;

@Named
@Scope("request")
public class CertificatesController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<Certificate> certificates;
	private Certificate certificate;

	public List<Certificate> getCertificates() {
		return certificates;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public void edit() {
		redirect(urlUtil.getCertificatesPath() + "/"
				+ getCertificate().getNodeId());
	}

	public void delete() {
		try {
			certificateRepository.delete(certificate);
			certificates.remove(certificate);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	@PostConstruct
	public void init() {
		certificates = Lists.newArrayList(certificateRepository.findAll());
	}
}
