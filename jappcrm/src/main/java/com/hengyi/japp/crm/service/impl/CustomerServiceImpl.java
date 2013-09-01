package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.CustomerIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.CustomerRepository;
import com.hengyi.japp.crm.service.CustomerService;

@Named
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Inject
	protected Neo4jOperations template;
	@Inject
	private CustomerRepository customerRepository;
	@Inject
	private CustomerIndicatorRepository customerIndicatorRepository;

	@Override
	public Customer findOne(Long nodeId) {
		return customerRepository.findOne(nodeId);
	}

	@Override
	public void save(Customer customer, CrmType crmType,
			Communicatee communicatee, Iterable<Communicatee> communicatees,
			Iterable<Associate> associates) {
		customer.setCrmType(crmType);
		customer.setCommunicatee(communicatee);
		customer.setCommunicatees(communicatees);
		customer.setAssociates(associates);
		customerRepository.save(customer);
		for (Associate associate : associates)
			template.save(associate);
	}

	@Override
	public void delete(Customer customer) throws Exception {
		customerRepository.delete(customer);
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
	public List<CustomerIndicator> findAllIndicator() {
		return Lists.newArrayList(customerIndicatorRepository.findAll());
	}
}
