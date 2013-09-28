package com.hengyi.japp.crm.event.customer;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.domain.customer.CustomerIndicator;

public class CustomerIndicatorUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3942193835269289143L;

	public CustomerIndicatorUpdateEvent(CustomerIndicator customerIndicator) {
		super(customerIndicator.getNodeId());
	}

	public CustomerIndicatorUpdateEvent(Long nodeId) {
		super(nodeId);
	}
}
