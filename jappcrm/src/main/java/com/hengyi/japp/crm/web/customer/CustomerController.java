package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.customer.CustomerService;
import com.hengyi.japp.crm.web.CrmController;

@Named
@Scope("view")
public class CustomerController extends CrmController<Customer> implements
		Serializable {
	private static final long serialVersionUID = 3708518912737819900L;
	@Inject
	private CustomerService customerService;

	@Override
	protected CrmService<Customer> getCrmService() {
		return customerService;
	}
}
