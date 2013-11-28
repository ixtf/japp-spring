package com.hengyi.japp.report.web;

import java.util.List;

import org.primefaces.model.LazyDataModel;

import com.hengyi.japp.common.web.model.LazyNeo4jModel;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportService;

@SuppressWarnings("unchecked")
public abstract class ReportsController<T extends Report> extends
		AbstractController {
	private LazyNeo4jModel<T> reports;
	private String nameSearch;
	private T report;

	protected abstract ReportService<T> getReportService();

	public void edit() {
		redirect(getReportService().getUpdatePath(getReport().getNodeId()));
	}

	public void delete() {
		try {
			getReportService().delete(getReport());
			List<T> list = (List<T>) reports.getWrappedData();
			if (list != null) {
				list.remove(getReport());
				reports = new LazyNeo4jModel<>(list);
			}
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void search() {
		try {
			reports = new LazyNeo4jModel<>(getReportService().findAllByQuery(
					nameSearch));
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public LazyDataModel<T> getReports() {
		if (reports == null)
			reports = new LazyNeo4jModel<>(getReportService());
		return reports;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public T getReport() {
		return report;
	}

	public void setReports(LazyNeo4jModel<T> reports) {
		this.reports = reports;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public void setReport(T report) {
		this.report = report;
	}
}
