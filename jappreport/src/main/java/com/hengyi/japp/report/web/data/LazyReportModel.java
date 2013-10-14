package com.hengyi.japp.report.web.data;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.service.ReportService;

public class LazyReportModel<T extends Report> extends LazyDataModel<T> {
	private static final long serialVersionUID = -3643294680833922852L;
	private ReportService<T> reportService;
	private List<T> datasource;

	public LazyReportModel(ReportService<T> reportService) {
		super();
		this.reportService = reportService;
		this.setRowCount((int) reportService.count());
	}

	public LazyReportModel(List<T> datasource) {
		this.datasource = datasource;
		this.setRowCount(datasource.size());
		this.setPageSize(datasource.size());
	}

	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (datasource == null)
			return fromDb(first, pageSize, sortField, sortOrder, filters);
		else
			return fromResult(first, pageSize, sortField, sortOrder, filters);
	}

	private List<T> fromResult(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		int dataSize = this.getRowCount();
		if (dataSize > pageSize) {
			try {
				return datasource.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return datasource.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return datasource;
		}
	}

	private List<T> fromDb(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<T> data = Lists.newArrayList();
		PageRequest pageRequest = new PageRequest(first / pageSize, pageSize);
		data = reportService.findAll(pageRequest);
		return data;
	}
}
