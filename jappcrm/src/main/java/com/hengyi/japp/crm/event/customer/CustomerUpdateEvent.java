package com.hengyi.japp.crm.event.customer;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.domain.customer.Customer;

public class CustomerUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3942193835269289143L;

	public CustomerUpdateEvent(Customer customer) {
		super(customer.getNodeId());
	}

	public CustomerUpdateEvent(Long nodeId) {
		super(nodeId);
	}
}
