package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.repository.CrmTypeRepository;
import com.hengyi.japp.crm.service.CrmTypeService;

@Named("crmTypeService")
@Transactional
@SuppressWarnings("unchecked")
public class CrmTypeServiceImpl extends AbstractCommonCrudNeo4jService<CrmType>
		implements CrmTypeService {
	@Resource
	private CrmTypeRepository crmTypeRepository;

	@Override
	public String getNewPath() {
		return "/crmType";
	}

	@Override
	public <RP extends Repository<CrmType, Long>> RP getRepository() {
		return (RP) crmTypeRepository;
	}
}
