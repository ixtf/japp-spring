package com.hengyi.japp.foreign.application.event;

import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;

@Named
public class EventPublisherImpl implements EventPublisher {
	private ApplicationContext publisher;

	@Override
	public void setApplicationContext(ApplicationContext publisher) {
		this.publisher = publisher;
	}

	@Async
	@Override
	public void publish(ApplicationEvent event) {
		publisher.publishEvent(event);
	}

}
