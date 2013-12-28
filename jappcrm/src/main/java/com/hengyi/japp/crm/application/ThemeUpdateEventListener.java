package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.event.ThemeUpdateEvent;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;

@Named
@Transactional
public class ThemeUpdateEventListener implements
		ApplicationListener<ThemeUpdateEvent> {
	@Inject
	private OperatorRepository operatorRepository;

	@Override
	public void onApplicationEvent(ThemeUpdateEvent event) {
		Operator operator = operatorRepository.findByPropertyValue(
				Operator.class.getSimpleName(), "uuid", event.getUuid());
		operator.setTheme(event.getTheme());
		operatorRepository.save(operator);
	}
}
