package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.event.CrmDeleteEvent;

@Named
@Transactional
public class CrmDeleteEventListener implements
		ApplicationListener<CrmDeleteEvent> {
	@Inject
	private CrmRepository crmRepository;

	@Override
	public void onApplicationEvent(CrmDeleteEvent event) {
		crmRepository.delete(event.getCrmNodeId());
	}
}
