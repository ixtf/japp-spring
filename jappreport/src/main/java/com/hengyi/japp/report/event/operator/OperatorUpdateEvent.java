package com.hengyi.japp.report.event.operator;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.report.domain.Operator;

public class OperatorUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3786875826980526554L;

	public OperatorUpdateEvent(Long nodeId) {
		super(nodeId);
	}

	public OperatorUpdateEvent(Operator operator) {
		this(operator.getNodeId());
	}

	public Long getNodeId() {
		return (Long) super.getSource();
	}
}
