package com.hengyi.japp.crm.web;

import java.util.List;

import javax.annotation.PostConstruct;

import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.service.ReportService;

public abstract class ReportsController<T extends Report> extends
		AbstractController {
	private ReportService<T> reportService;
	private List<T> reports;
	private T report;

	@PostConstruct
	private void init() {
		reportService = getReportService();
		reports = reportService.findAll();
	}

	protected abstract ReportService<T> getReportService();

	public abstract void edit();

	public void delete() {
		try {
			reportService.delete(report);
			reports.remove(report);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public List<T> getReports() {
		return reports;
	}

	public T getReport() {
		return report;
	}

	public void setReport(T report) {
		this.report = report;
	}
}
