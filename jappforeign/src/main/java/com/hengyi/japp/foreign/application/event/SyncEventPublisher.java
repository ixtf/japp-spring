package com.hengyi.japp.foreign.application.event;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public interface SyncEventPublisher extends ApplicationContextAware {
	void publish(ApplicationEvent event);
}
