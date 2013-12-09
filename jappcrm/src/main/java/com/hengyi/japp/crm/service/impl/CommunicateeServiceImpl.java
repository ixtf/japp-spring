package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.repository.CommunicateeRepository;
import com.hengyi.japp.crm.service.CommunicateeService;

@Named("communicateeService")
@Transactional
@SuppressWarnings("unchecked")
public class CommunicateeServiceImpl extends
		AbstractCommonCrudNeo4jService<Communicatee> implements
		CommunicateeService {
	@Resource
	private CommunicateeRepository communicateeRepository;

	@Override
	public List<Communicatee> findAllByQuery(String nameSearch)
			throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(communicateeRepository.findAllByQuery("name",
				nameSearch));
	}

	@Override
	public String getNewPath() {
		return "/communicatee";
	}

	@Override
	public <R extends Repository<Communicatee, Long>> R getRepository() {
		return (R) communicateeRepository;
	}

	@Override
	public void save(Communicatee communicatee) throws Exception {
		communicateeRepository.save(communicatee);
	}

	@Override
	public void delete(Communicatee communicatee) {
		communicateeRepository.delete(communicatee);
	}
}
