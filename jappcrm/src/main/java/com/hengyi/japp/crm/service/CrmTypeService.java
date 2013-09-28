package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.crm.domain.CrmType;

public interface CrmTypeService extends CommonUrlService<Long> {
	CrmType findOne(Long nodeId);

	void save(CrmType crmType) throws Exception;

	void delete(CrmType crmType) throws Exception;

	List<CrmType> findAll();
}
