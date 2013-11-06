package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.repository.CrmFieldRepository;
import com.hengyi.japp.crm.service.CrmFieldService;

@Named("crmFieldService")
@Transactional
@SuppressWarnings("unchecked")
public class CrmFieldServiceImpl extends
		AbstractCommonCrudNeo4jService<CrmField> implements CrmFieldService {
	@Resource
	private CrmFieldRepository crmFieldRepository;

	@Override
	public void save(CrmField report, Iterable<Indicator> indicators) {
		// TODO Auto-generated method stub

	}

	@Override
	public <R extends Repository<CrmField, Long>> R getRepository() {
		return (R) crmFieldRepository;
	}

	@Override
	public String getNewPath() {
		return "/admin/crmField";
	}
}
