package com.hengyi.japp.common.event;

import org.springframework.context.ApplicationEvent;

public class ThemeUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -7137063779676721729L;
	private final String uuid;

	public ThemeUpdateEvent(Object theme, String uuid) {
		super(theme);
		this.uuid = uuid;
	}

	public String getTheme() {
		return (String) getSource();
	}

	public String getUuid() {
		return uuid;
	}

}
