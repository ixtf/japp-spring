package com.hengyi.japp.trans.event;

import org.springframework.context.ApplicationEvent;

public abstract class AbstractApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID = -5317060417145460138L;
	private final Object type;

	public AbstractApplicationEvent(Object source, Object type) {
		super(source);
		this.type = type;
	}

	public Object getType() {
		return type;
	}
}
