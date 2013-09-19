package com.hengyi.japp.crm.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerBasicInfoReport;
import com.hengyi.japp.crm.domain.customer.CustomerCreditRiskReport;

public interface CustomerService {
	Customer findOne(Long nodeId);

	List<Customer> findAll(PageRequest pageRequest);

	long count();

	List<Customer> findAllByQuery(String nameSearch) throws Exception;

	List<Indicator> findAllIndicator();

	CustomerBasicInfoReport basicInfoReport(Long nodeId);

	CustomerCreditRiskReport creditRiskReport(Long nodeId);
}
