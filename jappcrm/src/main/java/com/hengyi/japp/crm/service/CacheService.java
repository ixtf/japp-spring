package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.Operator;

public interface CacheService extends CommonCacheService {
	Operator getCurrentOperator() throws Exception;

	List<Certificate> getAllCertificates();

	List<CorporationType> getAllCorporationTypes();
}
