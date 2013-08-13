package com.hengyi.japp.crm.web;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.node.customer.Customer;

@Named
@Scope("request")
public class HomeController extends AbstractController {
	private static final long serialVersionUID = 3708518912737819900L;
	private Customer customer;

	@PostConstruct
	private void init() {
		customer = new Customer();
	}
}
