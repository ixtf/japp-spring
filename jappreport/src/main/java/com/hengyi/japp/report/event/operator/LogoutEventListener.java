package com.hengyi.japp.report.event.operator;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;

@Named
@Singleton
@Transactional
public class LogoutEventListener implements ApplicationListener<LogoutEvent> {
	@Resource
	private EventBus eventBus;

	@Override
	public void onApplicationEvent(LogoutEvent event) {
		eventBus.post(event);
	}
}
