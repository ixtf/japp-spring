package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.event.CertificateDeleteEvent;

@Named
@Transactional
public class CertificateDeleteEventListener implements
		ApplicationListener<CertificateDeleteEvent> {
	@Inject
	private EventBus eventBus;
	@Inject
	private CertificateRepository certificateRepository;

	@Override
	public void onApplicationEvent(CertificateDeleteEvent event) {
		certificateRepository.delete(event.getCertificateNodeId());
		eventBus.post(event);
	}
}
