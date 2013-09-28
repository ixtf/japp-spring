package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.CustomerIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.StorageIndicatorRepository;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.customer.CustomerIndicatorService;
import com.hengyi.japp.crm.service.customer.CustomerService;

@Named("customerIndicatorService")
@Transactional
public class CustomerIndicatorServiceImpl extends
		IndicatorServiceImpl<CustomerIndicator> implements
		CustomerIndicatorService {
	@Inject
	private CustomerService customerService;
	@Resource
	protected CustomerIndicatorRepository customerIndicatorRepository;
	@Resource
	protected StorageIndicatorRepository storageIndicatorRepository;

	@Override
	public CustomerIndicator newIndicator() {
		return new CustomerIndicator();
	}

	@Override
	public CustomerIndicator findOne(Long nodeId) {
		return customerIndicatorRepository.findOne(nodeId);
	}

	@Override
	public List<CustomerIndicator> findAll() {
		return Lists.newArrayList(customerIndicatorRepository.findAll());
	}

	@Override
	protected CrmService<?> getCrmService() {
		return customerService;
	}
}
