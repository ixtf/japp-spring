package com.hengyi.japp.report.event.menu;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.report.domain.Menu;

public class MenuUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -2908577920167339932L;

	public MenuUpdateEvent(Long nodeId) {
		super(nodeId);
	}

	public MenuUpdateEvent(Menu menu) {
		this(menu.getNodeId());
	}

	public Long getNodeId() {
		return (Long) super.getSource();
	}
}
