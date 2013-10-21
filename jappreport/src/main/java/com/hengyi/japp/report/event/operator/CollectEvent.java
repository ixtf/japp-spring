package com.hengyi.japp.report.event.operator;

import org.springframework.context.ApplicationEvent;

public class CollectEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3786875826980526554L;
	private Long operatorNodeId;
	private Long reportNodeId;

	public CollectEvent(Object source, Long operatorNodeId, Long reportNodeId) {
		super(source);
		this.operatorNodeId = operatorNodeId;
		this.reportNodeId = reportNodeId;
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}

	public Long getReportNodeId() {
		return reportNodeId;
	}
}
