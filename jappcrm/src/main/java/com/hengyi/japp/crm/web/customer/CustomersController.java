package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.web.CrmsController;

@Named
@Scope("view")
public class CustomersController extends CrmsController<Customer> implements
		Serializable {
	private static final long serialVersionUID = 3708518912737819900L;

	public void edit() {
		redirect(urlUtil.getCustomersPath() + "/" + getCrm().getNodeId());
	}

	public void basicInfoReport() {
		redirect(urlUtil.getCustomersPath() + "/" + getCrm().getNodeId()
				+ "/report/basicInfo");
	}

	@Override
	protected CrmService<Customer> getCrmService() {
		return customerService;
	}
}
