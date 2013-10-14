package com.hengyi.japp.report.web;

import java.util.List;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportService;
import com.hengyi.japp.report.web.data.LazyReportModel;

public abstract class ReportsController<T extends Report> extends
		AbstractController {
	private LazyReportModel<T> reports;
	private String nameSearch;
	private List<T> searchResult;
	private T report;

	protected abstract ReportService<T> getReportService();

	public void edit() {
		redirect(getReportService().getUpdatePath(getReport().getNodeId()));
	}

	public void delete() {
		try {
			getReportService().delete(getReport());
			if (searchResult != null)
				searchResult.remove(getReport());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = getReportService().findAllByQuery(nameSearch);
			reports = new LazyReportModel<T>(searchResult);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public LazyReportModel<T> getReports() {
		if (reports == null)
			reports = new LazyReportModel<>(getReportService());
		return reports;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public List<T> getSearchResult() {
		return searchResult;
	}

	public T getReport() {
		return report;
	}

	public void setReports(LazyReportModel<T> reports) {
		this.reports = reports;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public void setSearchResult(List<T> searchResult) {
		this.searchResult = searchResult;
	}

	public void setReport(T report) {
		this.report = report;
	}
}
