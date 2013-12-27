package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.customer.CustomerReport;
import com.hengyi.japp.crm.service.ReportService;
import com.hengyi.japp.crm.service.customer.CustomerReportService;
import com.hengyi.japp.crm.service.customer.CustomerService;
import com.hengyi.japp.crm.web.ReportController;

@Named
@Scope("view")
public class CustomerReportController extends ReportController<CustomerReport>
		implements Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	@Inject
	private CustomerService customerService;
	@Inject
	private CustomerReportService customerReportService;
	private List<CrmField> allCrmFields;

	@Override
	protected ReportService<CustomerReport> getReportService() {
		return customerReportService;
	}

	@Override
	public List<CrmField> getAllCrmFields() {
		if (allCrmFields == null)
			allCrmFields = customerService.findAllCrmField();
		return allCrmFields;
	}

	public void setAllCrmFields(List<CrmField> allCrmFields) {
		this.allCrmFields = allCrmFields;
	}
}
