package com.hengyi.japp.crm.event.customer;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.event.indicator.IndicatorUpdateEvent;
import com.hengyi.japp.crm.event.publisher.SyncEventPublisher;

@Named
@Transactional
public class CustomerIndicatorUpdateEventListener implements
		ApplicationListener<CustomerIndicatorUpdateEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	protected SyncEventPublisher syncEventPublisher;

	@Override
	public void onApplicationEvent(CustomerIndicatorUpdateEvent event) {
		try {
			Long nodeId = (Long) event.getSource();
			syncEventPublisher.publish(new IndicatorUpdateEvent(nodeId));
		} catch (Exception e) {
			log.error("客户更新数据，指标分数计算失败！", e);
		}
	}
}
