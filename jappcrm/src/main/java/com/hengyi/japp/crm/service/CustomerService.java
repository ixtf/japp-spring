package com.hengyi.japp.crm.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerBasicInfoReport;
import com.hengyi.japp.crm.domain.customer.CustomerCreditRiskReport;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;

public interface CustomerService {
	Customer findOne(Long nodeId);

	void save(Customer customer, CrmType crmType, Communicatee communicatee,
			Iterable<Communicatee> communicatees, Iterable<Associate> associates);

	void delete(Customer customer) throws Exception;

	List<Customer> findAll(PageRequest pageRequest);

	long count();

	List<Customer> findAllByQuery(String nameSearch);

	List<CustomerIndicator> findAllIndicator();

	CustomerBasicInfoReport basicInfoReport(Long nodeId);

	CustomerCreditRiskReport creditRiskReport(Long nodeId);
}
