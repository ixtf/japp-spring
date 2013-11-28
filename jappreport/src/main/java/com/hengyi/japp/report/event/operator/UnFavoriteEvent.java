package com.hengyi.japp.report.event.operator;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;

public class UnFavoriteEvent extends ApplicationEvent {
	private static final long serialVersionUID = 578667437269185031L;
	private final Operator operator;

	public UnFavoriteEvent(Operator operator, Report report) {
		super(report);
		this.operator = operator;
	}

	public Report getReport() {
		return (Report) getSource();
	}

	public Operator getOperator() {
		return operator;
	}
}
