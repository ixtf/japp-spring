package com.hengyi.japp.common.web.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.common.service.CommonCrudNeo4jService;

@SuppressWarnings("unchecked")
public class LazyNeo4jModel<T extends AbstractNeo4j> extends LazyDataModel<T> {
	private static final long serialVersionUID = -2178617792174240026L;
	private CommonCrudNeo4jService<T> service;
	private List<T> datasource;

	public LazyNeo4jModel(CommonCrudNeo4jService<T> service) {
		super();
		this.service = service;
		setRowCount((int) service.count());
	}

	public LazyNeo4jModel(Iterable<T> i) {
		super();
		this.datasource = Lists.newArrayList(i);
		setWrappedData(datasource);
		setRowCount(datasource.size());
	}

	@Override
	public T getRowData(String rowKey) {
		try {
			Long nodeId = Long.valueOf(rowKey);
			List<T> datasource = (List<T>) getWrappedData();
			for (T t : datasource)
				if (t.getNodeId() == nodeId)
					return t;
			return service.findOne(nodeId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Object getRowKey(T object) {
		return object == null ? null : object.getNodeId();
	}

	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (service == null)
			return fromResult(first, pageSize, sortField, sortOrder, filters);
		else
			return fromDb(first, pageSize, sortField, sortOrder, filters);
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
		data = service.findAll(pageRequest);
		return data;
	}
}
