package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.service.CrmTypeService;

@Named("crmTypeService")
@Transactional
@SuppressWarnings("unchecked")
public class CrmTypeServiceImpl extends AbstractCommonCrudNeo4jService<CorporationType>
		implements CrmTypeService {
	@Resource
	private CorporationTypeRepository crmTypeRepository;

	@Override
	public String getNewPath() {
		return "/crmType";
	}

	@Override
	public <RP extends Repository<CorporationType, Long>> RP getRepository() {
		return (RP) crmTypeRepository;
	}

	@Override
	public void save(Iterable<CorporationType> crmTypes) {
		crmTypeRepository.save(crmTypes);
	}

	@Override
	public void save(CorporationType crmType) {
		crmTypeRepository.save(crmType);
	}

	@Override
	public void delete(CorporationType crmType) {
		crmTypeRepository.delete(crmType);
	}
}
