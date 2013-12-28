package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.event.CorporationTypeDeleteEvent;

@Named
@Transactional
public class CorporationTypeDeleteEventListener implements
		ApplicationListener<CorporationTypeDeleteEvent> {
	@Inject
	private EventBus eventBus;
	@Inject
	private CorporationTypeRepository corporationTypeRepository;

	@Override
	public void onApplicationEvent(CorporationTypeDeleteEvent event) {
		corporationTypeRepository.delete(event.getCorporationTypeNodeId());
		eventBus.post(event);
	}
}
