package com.hengyi.japp.foreign.event.invoice;

import org.springframework.context.ApplicationEvent;

public class InvoiceUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1559236270600268038L;

	public InvoiceUpdateEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
