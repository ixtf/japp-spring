package com.hengyi.japp.foreign.event;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public interface EventPublisher extends ApplicationContextAware {
	void publish(ApplicationEvent event);
}
