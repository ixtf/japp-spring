package com.hengyi.japp.foreign.event.likp;

import org.springframework.context.ApplicationEvent;

public class InvoiceWriteToSapEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1276943027325597789L;

	public InvoiceWriteToSapEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
