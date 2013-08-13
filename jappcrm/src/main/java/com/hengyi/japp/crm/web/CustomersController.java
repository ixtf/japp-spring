package com.hengyi.japp.crm.web;

import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.node.customer.Customer;

@Named
@Scope("request")
public class CustomersController extends AbstractController {
	private static final long serialVersionUID = 3708518912737819900L;
	private List<Customer> customers;
	private Customer customer;

	public List<Customer> getCustomers() {
		return customers;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
