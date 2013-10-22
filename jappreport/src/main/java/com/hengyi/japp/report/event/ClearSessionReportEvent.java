package com.hengyi.japp.report.event;

import com.hengyi.japp.report.domain.Operator;

public class ClearSessionReportEvent {
	private final Operator operator;

	public ClearSessionReportEvent(Operator operator) {
		super();
		this.operator = operator;
	}

	public Operator getOperator() {
		return operator;
	}

}
