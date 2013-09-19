package com.hengyi.japp.common.event.corporation;

import org.springframework.context.ApplicationEvent;

public class CorporationEvent extends ApplicationEvent {
	private static final long serialVersionUID = -5538522740447615400L;
	private final Type type;

	public CorporationEvent(final Object command, final Type type) {
		super(command);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public static enum Type {
		OA_CORPORATION_IMPORTED
	}

}
