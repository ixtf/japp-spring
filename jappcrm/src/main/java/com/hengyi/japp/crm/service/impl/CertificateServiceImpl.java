package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.service.CertificateService;

@Named("certificateService")
@Transactional
public class CertificateServiceImpl extends
		AbstractCommonCrudNeo4jService<Certificate> implements
		CertificateService {
	@Resource
	private CertificateRepository certificateRepository;

	@Override
	public String getNewPath() {
		return "/certificate";
	}

	@Override
	@SuppressWarnings("unchecked")
	public <RP extends Repository<Certificate, Long>> RP getRepository() {
		return (RP) certificateRepository;
	}
}
