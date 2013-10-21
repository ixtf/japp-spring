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
public class CollectEventListener implements ApplicationListener<CollectEvent> {
	@Resource
	private EventBus eventBus;

	@Override
	public void onApplicationEvent(CollectEvent event) {
		eventBus.post(event);
	}
}
