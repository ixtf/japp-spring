package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.crm.domain.repository.CrmFieldRepository;
import com.hengyi.japp.crm.event.CrmFieldDeleteEvent;

@Named
@Transactional
public class CrmFieldDeleteEventListener implements
		ApplicationListener<CrmFieldDeleteEvent> {
	@Inject
	private EventBus eventBus;
	@Inject
	private CrmFieldRepository crmFieldRepository;

	@Override
	public void onApplicationEvent(CrmFieldDeleteEvent event) {
		crmFieldRepository.delete(event.getCrmFieldNodeId());
		eventBus.post(event);
	}
}
