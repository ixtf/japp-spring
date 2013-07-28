package com.hengyi.japp.foreign.application.event;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public interface EventPublisher extends ApplicationContextAware {
	void publish(ApplicationEvent event);
}
