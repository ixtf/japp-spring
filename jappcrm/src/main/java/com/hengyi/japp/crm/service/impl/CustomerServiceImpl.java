package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.data.CrmFieldType;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.CustomerIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.CustomerRepository;
import com.hengyi.japp.crm.service.customer.CustomerService;

@Named("customerService")
@Transactional
@SuppressWarnings("unchecked")
public class CustomerServiceImpl extends CrmServiceImpl<Customer> implements
		CustomerService {
	@Resource
	private CustomerRepository customerRepository;
	@Resource
	private CustomerIndicatorRepository customerIndicatorRepository;

	@Override
	public List<Customer> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(customerRepository.findAllByQuery("name",
				nameSearch));
	}

	// @Override
	// public void save(Customer crm,
	// Map<Indicator, List<IndicatorValueScore>> indicatorMap,
	// Iterable<CorporationType> corporationTypes,
	// Iterable<Certificate> certificates, Communicatee communicatee,
	// Iterable<Communicatee> communicatees, Iterable<Associate> associates)
	// throws Exception {
	// super.save(crm, indicatorMap, corporationTypes, certificates,
	// communicatee, communicatees, associates);
	// final CustomerUpdateEvent event = new CustomerUpdateEvent(
	// crm.getNodeId());
	// if (TransactionSynchronizationManager.isActualTransactionActive())
	// TransactionSynchronizationManager
	// .registerSynchronization(new TransactionSynchronizationAdapter() {
	// @Override
	// public void afterCommit() {
	// eventPublisher.publish(event);
	// }
	// });
	// else
	// eventPublisher.publish(event);
	// }

	@Override
	public List<Indicator> findAllIndicator() {
		List<Indicator> result = Lists.newArrayList();
		for (CustomerIndicator indicator : customerIndicatorRepository
				.findAll())
			result.add(indicator);
		return result;
	}

	@Override
	public List<CrmField> findAllCrmField() {
		List<CrmField> result = findAllCrmField(CrmFieldType.CRM);
		result.addAll(findAllCrmField(CrmFieldType.CUSTOMER));
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

	@Override
	public <R extends Repository<Customer, Long>> R getRepository() {
		return (R) customerRepository;
	}
}
