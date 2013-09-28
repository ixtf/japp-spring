package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.customer.CustomerReport;
import com.hengyi.japp.crm.service.CustomerReportService;

@Named
@Transactional
public class CustomerReportServiceImpl extends
		ReportServiceImpl<CustomerReport> implements CustomerReportService {

	@Override
	public CustomerReport findOne(Long nodeId) {
		return customerReportRepository.findOne(nodeId);
	}

	@Override
	public List<CustomerReport> findAll() {
		return Lists.newArrayList(customerReportRepository.findAll());
	}
}
