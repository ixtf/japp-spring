package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.customer.CustomerReport;
import com.hengyi.japp.crm.service.ReportService;
import com.hengyi.japp.crm.service.customer.CustomerReportService;
import com.hengyi.japp.crm.web.ReportsController;

@Named
@Scope("view")
public class CustomerReportsController extends
		ReportsController<CustomerReport> implements Serializable {
	private static final long serialVersionUID = 7250376086420104890L;
	@Inject
	private CustomerReportService customerReportService;

	@Override
	protected ReportService<CustomerReport> getReportService() {
		return customerReportService;
	}
}
