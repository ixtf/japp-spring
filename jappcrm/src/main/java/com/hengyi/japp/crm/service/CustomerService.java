package com.hengyi.japp.crm.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;

public interface CustomerService extends IndicatorService<CustomerIndicator> {
	Customer findOne(Long nodeId);

	void save(Customer customer, CrmType crmType, Communicatee communicatee,
			Iterable<Communicatee> communicatees, Iterable<Associate> associates);

	void delete(Customer customer) throws Exception;

	List<Customer> findAll(PageRequest pageRequest);

	long count();

	List<Customer> findAllByQuery(String nameSearch);
}
