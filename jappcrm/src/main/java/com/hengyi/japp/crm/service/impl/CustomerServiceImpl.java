package com.hengyi.japp.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.CustomerIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.CustomerRepository;
import com.hengyi.japp.crm.event.customer.CustomerUpdateEvent;
import com.hengyi.japp.crm.service.customer.CustomerService;

@Named("customerService")
@Transactional
public class CustomerServiceImpl extends CrmServiceImpl<Customer> implements
		CustomerService {
	@Resource
	private CustomerRepository customerRepository;
	@Resource
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
	public List<Customer> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(customerRepository.findAllByQuery("name",
				nameSearch));
	}

	@Override
	public void save(Customer crm,
			Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			Iterable<CrmType> crmTypes, Iterable<Certificate> certificates,
			Communicatee communicatee, Iterable<Communicatee> communicatees,
			Iterable<Associate> associates) throws Exception {
		super.save(crm, indicatorMap, crmTypes, certificates, communicatee,
				communicatees, associates);

		final CustomerUpdateEvent event = new CustomerUpdateEvent(
				crm.getNodeId());
		if (TransactionSynchronizationManager.isActualTransactionActive())
			TransactionSynchronizationManager
					.registerSynchronization(new TransactionSynchronizationAdapter() {
						@Override
						public void afterCommit() {
							eventPublisher.publish(event);
						}
					});
		else
			eventPublisher.publish(event);
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
	public Customer newCrm() {
		return new Customer();
	}

	@Override
	public String getNewPath() {
		return "/customer";
	}
}
