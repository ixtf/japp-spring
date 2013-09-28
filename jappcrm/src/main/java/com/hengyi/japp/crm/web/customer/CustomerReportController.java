package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.customer.CustomerReport;
import com.hengyi.japp.crm.service.ReportService;
import com.hengyi.japp.crm.web.ReportController;

@Named
@Scope("view")
public class CustomerReportController extends ReportController<CustomerReport>
		implements Serializable {
	private static final long serialVersionUID = -6359781138513690580L;

	@Override
	protected CustomerReport newReport() {
		return new CustomerReport();
	}

	@Override
	protected ReportService<CustomerReport> getReportService() {
		return customerReportService;
	}
}
