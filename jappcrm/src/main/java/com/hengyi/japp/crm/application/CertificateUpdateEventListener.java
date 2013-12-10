package com.hengyi.japp.crm.application;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.event.CertificateUpdateEvent;

@Component
@Transactional
public class CertificateUpdateEventListener implements
		ApplicationListener<CertificateUpdateEvent> {
	@Inject
	private Mapper dozer;
	@Inject
	private OperatorRepository operatorRepository;
	@Inject
	private CertificateRepository certificateRepository;

	@Override
	public void onApplicationEvent(CertificateUpdateEvent event) {
		Certificate entity = null;
		CertificateDTO dto = event.getCertificate();
		if (dto.getNodeId() == null)
			entity = new Certificate();
		else
			entity = certificateRepository.findOne(dto.getNodeId());
		dozer.map(event.getSource(), entity);
		entity.setOperator(operatorRepository.findOne(event.getOperatorNodeId()));
		certificateRepository.save(entity);
		dto.setNodeId(entity.getNodeId());
	}

}
