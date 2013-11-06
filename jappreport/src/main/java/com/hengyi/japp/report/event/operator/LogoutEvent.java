package com.hengyi.japp.report.event.operator;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.report.domain.Operator;

public class LogoutEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3786875826980526554L;

	public LogoutEvent(Operator operator) {
		super(operator);
	}

	public Operator getOperator() {
		return (Operator) super.getSource();
	}
}
