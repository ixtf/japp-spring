package com.hengyi.japp.common.application.event;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;

public interface EventPublisher extends ApplicationContextAware {
	@Async
	void publish(ApplicationEvent event);
}
