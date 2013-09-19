package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import com.hengyi.japp.crm.domain.report.Report;

public abstract class ReportsController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<Report> reports;
	private Report report;

	public abstract void edit();

	protected abstract List<Report> findAllReports();

	public void delete() {
		try {
			reprotService.delete(report);
			reports.remove(report);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public List<Report> getReports() {
		if (reports == null)
			reports = findAllReports();
		return reports;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
}
