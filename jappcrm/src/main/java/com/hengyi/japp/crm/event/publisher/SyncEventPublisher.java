package com.hengyi.japp.crm.event.publisher;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public interface SyncEventPublisher extends ApplicationContextAware {
	void publish(ApplicationEvent event);
}
