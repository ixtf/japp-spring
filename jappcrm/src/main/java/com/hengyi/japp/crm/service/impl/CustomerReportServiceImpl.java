package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.customer.CustomerReport;
import com.hengyi.japp.crm.domain.repository.CustomerReportRepository;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.customer.CustomerReportService;
import com.hengyi.japp.crm.service.customer.CustomerService;

@Named("customerReportService")
@Transactional
@SuppressWarnings("unchecked")
public class CustomerReportServiceImpl extends
		ReportServiceImpl<CustomerReport> implements CustomerReportService {
	@Inject
	private CustomerService customerService;

	@Resource
	protected CustomerReportRepository customerReportRepository;

	@Override
	public CustomerReport newReport() {
		return new CustomerReport();
	}

	@Override
	protected CrmService<? extends Crm> getCrmService() {
		return customerService;
	}

	@Override
	public <R extends Repository<CustomerReport, Long>> R getRepository() {
		return (R) customerReportRepository;
	}
}
