package com.hengyi.japp.report.web.data;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.service.OperatorService;

public class LazyOperatorModel extends LazyDataModel<Operator> {
	private static final long serialVersionUID = -3643294680833922852L;
	private OperatorService operatorService;
	private List<Operator> datasource;

	public LazyOperatorModel(OperatorService operatorService) {
		super();
		this.operatorService = operatorService;
		this.setRowCount((int) operatorService.count());
	}

	public LazyOperatorModel(List<Operator> datasource) {
		this.datasource = datasource;
		this.setRowCount(datasource.size());
		this.setPageSize(datasource.size());
	}

	public List<Operator> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (datasource == null)
			return fromDb(first, pageSize, sortField, sortOrder, filters);
		else
			return fromResult(first, pageSize, sortField, sortOrder, filters);
	}

	private List<Operator> fromResult(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, String> filters) {
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

	private List<Operator> fromDb(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Operator> data = Lists.newArrayList();
		PageRequest pageRequest = new PageRequest(first / pageSize, pageSize);
		data = operatorService.findAll(pageRequest);
		return data;
	}
}
