package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.customer.CustomerReport;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.customer.CustomerReportService;
import com.hengyi.japp.crm.service.customer.CustomerService;
import com.hengyi.japp.crm.web.CrmsController;

@Named
@Scope("view")
public class CustomersController extends CrmsController<Customer> implements
		Serializable {
	private static final long serialVersionUID = 3708518912737819900L;
	@Inject
	private CustomerService customerService;
	@Inject
	private CustomerReportService customerReportService;

	public void basicInfoReport() {
		// TODO 报表连接重新修改
		redirect(customerService.getUpdatePath(getCrm().getNodeId())
				+ "/report/basicInfo");
	}

	@Override
	protected CrmService<Customer> getCrmService() {
		return customerService;
	}

	@Override
	protected List<CustomerReport> getReports() {
		return customerReportService.findAll();
	}
}
