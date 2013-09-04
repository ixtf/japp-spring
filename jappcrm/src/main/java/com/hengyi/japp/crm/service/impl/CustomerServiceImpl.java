package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerBasicInfoReport;
import com.hengyi.japp.crm.domain.customer.CustomerCreditRiskReport;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.CustomerIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.CustomerRepository;
import com.hengyi.japp.crm.service.CacheServiceFacade;
import com.hengyi.japp.crm.service.CustomerService;

@Named
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Inject
	private Neo4jOperations template;
	// @Inject
	// private EventBus eventBus;
	@Inject
	private CacheServiceFacade cacheServiceFacade;
	@Inject
	private CustomerRepository customerRepository;
	@Inject
	private CustomerIndicatorRepository customerIndicatorRepository;

	@Override
	public Customer findOne(Long nodeId) {
		return customerRepository.findOne(nodeId);
	}

	@Override
	public List<Customer> findAll(PageRequest pageRequest) {
		return Lists.newArrayList(customerRepository.findAll(pageRequest));
	}

	@Override
	public long count() {
		return customerRepository.count();
	}

	@Override
	public List<Customer> findAllByQuery(String nameSearch) {
		return Lists.newArrayList(customerRepository.findAllByQuery("name",
				nameSearch));
	}

	@Override
	public List<Indicator> findAllIndicator() {
		List<Indicator> result = Lists.newArrayList();
		for (CustomerIndicator indicator : customerIndicatorRepository
				.findAll())
			result.add(indicator);
		return result;
	}

	@Override
	public CustomerBasicInfoReport basicInfoReport(Long nodeId) {
		// TODO 修改报表的实现，不用entity去implement
		return null;
	}

	@Override
	public CustomerCreditRiskReport creditRiskReport(Long nodeId) {
		// TODO 修改报表的实现，不用entity去implement
		return null;
	}
}
