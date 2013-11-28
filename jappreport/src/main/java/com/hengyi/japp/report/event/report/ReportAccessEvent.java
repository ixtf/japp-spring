package com.hengyi.japp.report.event.report;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;

public class ReportAccessEvent extends ApplicationEvent {
	private static final long serialVersionUID = 5562319351620872287L;
	private final Operator operator;

	public ReportAccessEvent(Report report, Operator operator) {
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
