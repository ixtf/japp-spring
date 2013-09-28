package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.repository.CrmTypeRepository;
import com.hengyi.japp.crm.service.CrmTypeService;

@Named("crmTypeService")
@Transactional
public class CrmTypeServiceImpl extends CommonUrlServiceImpl<Long> implements
		CrmTypeService {
	@Resource
	private CrmTypeRepository crmTypeRepository;

	@Override
	public CrmType findOne(Long nodeId) {
		return nodeId == null ? null : crmTypeRepository.findOne(nodeId);
	}

	@Override
	public void save(CrmType crmType) throws Exception {
		crmTypeRepository.save(crmType);
	}

	@Override
	public void delete(CrmType crmType) throws Exception {
		crmTypeRepository.delete(crmType);
	}

	@Override
	public List<CrmType> findAll() {
		return Lists.newArrayList(crmTypeRepository.findAll());
	}

	@Override
	public String getNewPath() {
		return "/crmType";
	}
}
