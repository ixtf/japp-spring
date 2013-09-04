package com.hengyi.japp.crm.event.crm;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.service.CustomerService;
import com.hengyi.japp.crm.service.StorageService;

@Named
@Transactional
public class CrmUpdateEventListener implements
		ApplicationListener<CrmUpdateEvent> {
	@Inject
	private Neo4jOperations template;
	@Inject
	private CustomerService customerService;
	@Inject
	private StorageService storageService;

	@Override
	public void onApplicationEvent(CrmUpdateEvent event) {
		Crm crm = (Crm) event.getSource();
		Iterable<Indicator> indicators = null;
		if (crm instanceof Customer) {
			indicators = customerService.findAllIndicator();
		} else if (crm instanceof Storage) {
			indicators = customerService.findAllIndicator();
		}
		for (Indicator indicator : indicators)
			crm.indicatorScore(indicator, template);
	}
}
