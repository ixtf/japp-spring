package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.Certificate;

public interface CertificateService extends CommonCrudNeo4jService<Certificate> {
	Certificate findOne(Long nodeId);

	void save(Certificate certificate) throws Exception;

	void delete(Certificate certificate) throws Exception;

	List<Certificate> findAll();

	void save(Iterable<Certificate> certificates);
}
