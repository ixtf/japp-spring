package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.service.CertificateService;

@Named("certificateService")
@Transactional
public class CertificateServiceImpl extends CommonUrlServiceImpl<Long>
		implements CertificateService {
	@Resource
	private CertificateRepository certificateRepository;

	@Override
	public Certificate findOne(Long nodeId) {
		return nodeId == null ? null : certificateRepository.findOne(nodeId);
	}

	@Override
	public void save(Certificate certificate) throws Exception {
		certificateRepository.save(certificate);
	}

	@Override
	public void delete(Certificate certificate) throws Exception {
		certificateRepository.delete(certificate);
	}

	@Override
	public List<Certificate> findAll() {
		return Lists.newArrayList(certificateRepository.findAll());
	}

	@Override
	public String getNewPath() {
		return "/certificate";
	}
}
