package com.hengyi.japp.report.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.hengyi.japp.common.event.EventPublisher;

@Component
public class EventPublisherImpl implements EventPublisher {
	private ApplicationContext publisher;

	@Override
	public void setApplicationContext(ApplicationContext publisher) {
		this.publisher = publisher;
	}

	@Override
	public void publish(ApplicationEvent event) {
		publisher.publishEvent(event);
	}
}
