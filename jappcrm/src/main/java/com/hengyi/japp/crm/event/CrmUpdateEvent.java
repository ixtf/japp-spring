package com.hengyi.japp.crm.event;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.dto.CrmDTO;

public class CrmUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = 7820361085553280539L;
	private final Long operatorNodeId;
	private final Iterable<Long> indicatorValueIds;

	public CrmUpdateEvent(Long operatorNodeId, CrmDTO crm,
			Iterable<Long> indicatorValueIds) {
		super(crm);
		this.operatorNodeId = operatorNodeId;
		this.indicatorValueIds = indicatorValueIds;
	}

	public CrmDTO getCrm() {
		return (CrmDTO) getSource();
	}

	public Iterable<Long> getIndicatorValueIds() {
		return indicatorValueIds;
	}

	public Long getOperatorNodeId() {
		return operatorNodeId;
	}
}
