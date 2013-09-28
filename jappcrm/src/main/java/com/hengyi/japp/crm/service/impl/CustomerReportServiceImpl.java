package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.customer.CustomerReport;
import com.hengyi.japp.crm.domain.repository.CustomerReportRepository;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.customer.CustomerReportService;
import com.hengyi.japp.crm.service.customer.CustomerService;

@Named("customerReportService")
@Transactional
public class CustomerReportServiceImpl extends
		ReportServiceImpl<CustomerReport> implements CustomerReportService {
	@Inject
	private CustomerService customerService;

	@Resource
	protected CustomerReportRepository customerReportRepository;

	@Override
	public CustomerReport findOne(Long nodeId) {
		return customerReportRepository.findOne(nodeId);
	}

	@Override
	public List<CustomerReport> findAll() {
		return Lists.newArrayList(customerReportRepository.findAll());
	}

	@Override
	public CustomerReport newReport() {
		return new CustomerReport();
	}

	@Override
	protected CrmService<? extends Crm> getCrmService() {
		return customerService;
	}
}
