package com.hengyi.japp.crm.service.impl;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator.Indicator;
import com.hengyi.japp.crm.domain.repository.CustomerIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.CustomerRepository;
import com.hengyi.japp.crm.service.CustomerService;

@Named
@Transactional
public class CustomerServiceImpl implements CustomerService {
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
	public CustomerIndicator findOneIndicator(Long nodeId) {
		return customerIndicatorRepository.findOne(nodeId);
	}

	@Override
	public CustomerIndicator findOneIndicator(String name) {
		return customerIndicatorRepository.findByPropertyValue(
				CustomerIndicator.class.getSimpleName(), "name", name);
	}

	@Override
	public void save(CustomerIndicator indicator,
			Iterable<IndicatorValueScore> indicatorValueScores)
			throws Exception {
		indicator.setIndicatorValueScores(indicatorValueScores);
		customerIndicatorRepository.save(indicator);
	}

	@Override
	public void delete(CustomerIndicator indicator) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<CustomerIndicator> findAllIndicator() {
		return Lists.newArrayList(customerIndicatorRepository.findAll());
	}

	// @Override
	// public CustomerIndicator findSaleIncomeIndicator() {
	// return findOneIndicator(Indicator.SALEINCOME);
	// }

	@PostConstruct
	public void intIndicators() {
		ResourceBundle rb = ResourceBundle.getBundle("customerIndicators");
		for (String key : rb.keySet()) {

		}

		if (findOneIndicator(Indicator.BRAND) != null)
			return;
		CustomerIndicator indicator = new CustomerIndicator();
		indicator.setName(Indicator.BRAND);
	}
}
