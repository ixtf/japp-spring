package com.hengyi.japp.report.web;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportService;

public abstract class ReportController<T extends Report> extends
		AbstractController {
	private Long nodeId;
	private Class<T> clazz;
	private T report;
	private ReportService<T> reportService;
	@NotNull
	private Menu menu;

	@PostConstruct
	private void init() {
		reportService = getReportService();
		System.out.println(clazz);
	}

	protected abstract ReportService<T> getReportService();

	protected abstract String getUrl(T report);

	public void save() {
		try {
			reportService.save(getReport(), getMenu());
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

	public Menu getMenu() {
		if (menu == null)
			menu = getReport().getMenu();
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
