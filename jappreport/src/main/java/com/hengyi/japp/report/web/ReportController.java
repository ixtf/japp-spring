package com.hengyi.japp.report.web;

import javax.annotation.PostConstruct;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportService;

public abstract class ReportController<T extends Report> extends
		AbstractController {
	private Long nodeId;
	private T report;
	private ReportService<T> reportService;

	@PostConstruct
	private void init() {
		reportService = getReportService();
	}

	protected abstract ReportService<T> getReportService();

	protected abstract String getUrl(T report);

	public void save() {
		try {
			reportService.save(getReport());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public Long getNodeId() {
		return nodeId;
	}

	public T getReport() {
		if (report != null)
			return report;
		if (getNodeId() == null)
			report = reportService.newReport();
		else
			report = reportService.findOne(getNodeId());
		return report;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setReport(T report) {
		this.report = report;
	}
}
