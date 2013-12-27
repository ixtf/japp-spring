package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

public class CrmFileRemoveEvent extends ApplicationEvent {
	private static final long serialVersionUID = -5538522740447615400L;

	public CrmFileRemoveEvent(Object source) {
		super(source);
	}

	public Long getUploadFileNodeId() {
		return (Long) getSource();
	}
}
