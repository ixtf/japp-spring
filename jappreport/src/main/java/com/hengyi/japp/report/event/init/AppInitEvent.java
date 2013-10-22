package com.hengyi.japp.report.event.init;

import org.springframework.context.ApplicationEvent;

public class AppInitEvent extends ApplicationEvent {
	private static final long serialVersionUID = -5538522740447615400L;

	public AppInitEvent(Object source) {
		super(source);
	}
}
