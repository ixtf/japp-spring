package com.hengyi.japp.crm.event.crm;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.domain.Crm;

public class CrmUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3942193835269289143L;

	public CrmUpdateEvent(Crm crm) {
		super(crm);
	}
}
