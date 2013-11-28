package com.hengyi.japp.report.event.role;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;

@Named
@Singleton
@Transactional
public class RoleUpdateEventListener implements
		ApplicationListener<RoleUpdateEvent> {
	@Resource
	private EventBus eventBus;

	@Override
	public void onApplicationEvent(RoleUpdateEvent event) {
//		eventBus.post(event);
	}
}
