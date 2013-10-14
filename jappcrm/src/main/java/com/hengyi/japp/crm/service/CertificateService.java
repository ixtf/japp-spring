package com.hengyi.japp.crm.service;

import java.io.Serializable;
import java.util.List;

import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.crm.domain.Certificate;

public interface CertificateService extends CommonUrlService<Long>,
		Serializable {
	Certificate findOne(Long nodeId);

	void save(Certificate certificate) throws Exception;

	void delete(Certificate certificate) throws Exception;

	List<Certificate> findAll();
}
