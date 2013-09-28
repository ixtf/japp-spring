package com.hengyi.japp.crm.event.customer;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.service.customer.CustomerService;

@Named
@Transactional
public class CustomerUpdateEventListener implements
		ApplicationListener<CustomerUpdateEvent> {
	@Inject
	private CustomerService customerService;

	@Override
	public void onApplicationEvent(CustomerUpdateEvent event) {
		Long nodeId = (Long) event.getSource();
		Customer customer = customerService.findOne(nodeId);
		Iterable<Indicator> indicators = customerService.findAllIndicator();
		for (Indicator indicator : indicators)
			customer.indicatorScore(indicator);
	}
}
