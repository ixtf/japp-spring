package com.hengyi.japp.crm.web;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Customer;

@Named
@Scope("request")
public class CustomerController extends AbstractController {
	private static final long serialVersionUID = 3708518912737819900L;
	private String uuid;
	private Customer customer;

	public String getUuid() {
		return uuid;
	}

	public Customer getCustomer() {
		if (customer == null)
			customer = new Customer();
		return customer;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
		customer = customerService.findOne(uuid);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void save() {
		try {
			customerService.save(getCustomer());
			redirect("customers");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}
}
