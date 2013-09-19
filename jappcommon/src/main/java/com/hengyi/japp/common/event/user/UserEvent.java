package com.hengyi.japp.common.event.user;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {
	private static final long serialVersionUID = -5538522740447615400L;
	private final Type type;

	public UserEvent(final Object command, final Type type) {
		super(command);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public static enum Type {
		HR_USER_CREATED
	}

}
