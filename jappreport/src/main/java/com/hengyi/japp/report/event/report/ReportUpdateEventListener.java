package com.hengyi.japp.report.event.report;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;

@Named
@Singleton
@Transactional
public class ReportUpdateEventListener implements
		ApplicationListener<ReportUpdateEvent> {
	@Resource
	private EventBus eventBus;

	@Override
	public void onApplicationEvent(ReportUpdateEvent event) {
//		eventBus.post(event);
	}
}
