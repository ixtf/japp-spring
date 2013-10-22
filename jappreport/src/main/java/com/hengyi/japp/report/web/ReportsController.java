package com.hengyi.japp.report.web;

import java.util.List;

import org.primefaces.model.LazyDataModel;

import com.hengyi.japp.common.web.model.LazyNeo4jModel;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportService;

public abstract class ReportsController<T extends Report> extends
		AbstractController {
	private LazyDataModel<T> reports;
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
			reports = new LazyNeo4jModel<>(searchResult);
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

	public List<T> getSearchResult() {
		return searchResult;
	}

	public T getReport() {
		return report;
	}

	public void setReports(LazyDataModel<T> reports) {
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
