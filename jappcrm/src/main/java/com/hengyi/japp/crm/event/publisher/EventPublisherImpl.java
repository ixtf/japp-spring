package com.hengyi.japp.crm.event.publisher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

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
