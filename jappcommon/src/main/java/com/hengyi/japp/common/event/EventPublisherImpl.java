package com.hengyi.japp.common.event;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

@Named
@Singleton
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
