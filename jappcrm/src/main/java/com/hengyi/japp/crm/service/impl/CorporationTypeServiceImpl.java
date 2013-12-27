package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.service.CorporationTypeService;

@Named("corporationTypeService")
@Transactional
@SuppressWarnings("unchecked")
public class CorporationTypeServiceImpl extends
		AbstractCommonCrudNeo4jService<CorporationType> implements
		CorporationTypeService {
	@Resource
	private CorporationTypeRepository corporationTypeRepository;

	@Override
	public String getNewPath() {
		return "/corporationType";
	}

	@Override
	public <RP extends Repository<CorporationType, Long>> RP getRepository() {
		return (RP) corporationTypeRepository;
	}

	@Override
	public void save(Iterable<CorporationType> crmTypes) {
		corporationTypeRepository.save(crmTypes);
	}

	@Override
	public void save(CorporationType crmType) {
		corporationTypeRepository.save(crmType);
	}

	@Override
	public void delete(CorporationType crmType) {
		corporationTypeRepository.delete(crmType);
	}
}
