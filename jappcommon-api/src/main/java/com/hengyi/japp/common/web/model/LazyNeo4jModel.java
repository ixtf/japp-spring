package com.hengyi.japp.common.web.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.CommonCrudNeo4jService;

public class LazyNeo4jModel<T> extends LazyDataModel<T> {
	private static final long serialVersionUID = -2178617792174240026L;
	private CommonCrudNeo4jService<T> servcie;
	private List<T> datasource;

	public LazyNeo4jModel(CommonCrudNeo4jService<T> servcie) {
		super();
		this.servcie = servcie;
		this.setRowCount((int) servcie.count());
	}

	public LazyNeo4jModel(List<T> datasource) {
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
		data = servcie.findAll(pageRequest);
		return data;
	}
}
