package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.event.CustomerUpdateEvent;
import com.hengyi.japp.crm.service.customer.CustomerService;

@Named
@Transactional
public class CustomerUpdateEventListener implements
		ApplicationListener<CustomerUpdateEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	private CustomerService customerService;
	@Inject
	private Neo4jTemplate template;

	@Override
	public void onApplicationEvent(CustomerUpdateEvent event) {
		try {
			Long nodeId = (Long) event.getSource();
			Customer customer = customerService.findOne(nodeId);
			Iterable<Indicator> indicators = customerService.findAllIndicator();
			for (Indicator indicator : indicators)
				customer.indicatorScore(template, indicator);
		} catch (Exception e) {
			log.error("客户更新数据，指标分数计算失败！", e);
		}
	}
}
