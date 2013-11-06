package com.hengyi.japp.report.event.report;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.report.domain.Report;

public class ReportUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -2383876445502266382L;

	public ReportUpdateEvent(Long nodeId) {
		super(nodeId);
	}

	public ReportUpdateEvent(Report report) {
		this(report.getNodeId());
	}

	public Long getNodeId() {
		return (Long) super.getSource();
	}
}
