package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.event.CertificateUpdateEvent;

@Named
@Transactional
public class CertificateUpdateEventListener implements
		ApplicationListener<CertificateUpdateEvent> {
	@Inject
	private Mapper dozer;
	@Inject
	private OperatorRepository operatorRepository;
	@Inject
	private EventBus eventBus;
	@Inject
	private CertificateRepository certificateRepository;

	@Override
	public void onApplicationEvent(CertificateUpdateEvent event) {
		CertificateDTO dto = event.getCertificate();
		Long nodeId = dto.getNodeId();
		Certificate certificate = nodeId == null ? new Certificate()
				: certificateRepository.findOne(nodeId);
		dozer.map(dto, certificate);
		certificate.setOperator(operatorRepository.findOne(event
				.getOperatorNodeId()));
		certificateRepository.save(certificate);
		dto.setNodeId(certificate.getNodeId());
		eventBus.post(event);
	}
}
