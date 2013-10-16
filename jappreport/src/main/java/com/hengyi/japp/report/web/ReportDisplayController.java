package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.domain.Report;

@Named
@Scope("view")
public class ReportDisplayController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 9146840004984249764L;
	private Long nodeId;
	private Report report;
	private String url;

	public Report getReport() {
		if (report != null)
			return report;
		if (getNodeId() == null)
			return null;
		else
			report = cacheService.findOneReport(nodeId);
		return report;
	}

	public String getUrl() {
		if (url == null)
			url = getReport().getUrl();
		return url;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
}
