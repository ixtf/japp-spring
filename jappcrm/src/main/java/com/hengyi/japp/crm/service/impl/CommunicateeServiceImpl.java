package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.repository.CommunicateeRepository;
import com.hengyi.japp.crm.service.CommunicateeService;

@Named("communicateeService")
@Transactional
public class CommunicateeServiceImpl extends CommonUrlServiceImpl<Long>
		implements CommunicateeService {
	@Resource
	private CommunicateeRepository communicateeRepository;

	@Override
	public Communicatee findOne(Long nodeId) {
		return nodeId == null ? null : communicateeRepository.findOne(nodeId);
	}

	@Override
	public void save(Communicatee customer) throws Exception {
		communicateeRepository.save(customer);
	}

	@Override
	public void delete(Communicatee communicatee) throws Exception {
		communicateeRepository.delete(communicatee);
	}

	@Override
	public List<Communicatee> findAll(Pageable pageRequest) {
		return Lists.newArrayList(communicateeRepository.findAll(pageRequest));
	}

	@Override
	public List<Communicatee> findAllByQuery(String nameSearch)
			throws Exception {
		MyUtil.checkSearch(nameSearch);
		// nameSearch = StringUtils.trimToEmpty(nameSearch);
		return Lists.newArrayList(communicateeRepository.findAllByQuery("name",
				nameSearch));
	}

	@Override
	public long count() {
		return communicateeRepository.count();
	}

	@Override
	public String getNewPath() {
		return "/communicatee";
	}
}
