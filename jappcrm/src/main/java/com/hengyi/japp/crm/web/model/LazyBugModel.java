package com.hengyi.japp.crm.web.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Bug;
import com.hengyi.japp.crm.service.BugService;

public class LazyBugModel extends LazyDataModel<Bug> {
	private static final long serialVersionUID = 4941079990169518007L;
	private BugService bugService;
	private List<Bug> datasource;

	public LazyBugModel(BugService bugService) {
		super();
		this.bugService = bugService;
		this.setRowCount((int) bugService.count());
	}

	public LazyBugModel(List<Bug> datasource) {
		this.datasource = datasource;
		this.setRowCount(datasource.size());
		this.setPageSize(datasource.size());
	}

	public List<Bug> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (datasource == null)
			return fromDb(first, pageSize, sortField, sortOrder, filters);
		else
			return fromResult(first, pageSize, sortField, sortOrder, filters);
	}

	private List<Bug> fromResult(int first, int pageSize, String sortField,
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

	private List<Bug> fromDb(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Bug> data = Lists.newArrayList();
		PageRequest pageRequest = new PageRequest(first / pageSize, pageSize);
		data = bugService.findAll(pageRequest);
		return data;
	}
}
