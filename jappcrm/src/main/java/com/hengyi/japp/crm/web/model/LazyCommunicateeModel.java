package com.hengyi.japp.crm.web.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.service.CommunicateeService;

public class LazyCommunicateeModel extends LazyDataModel<Communicatee> {
	private static final long serialVersionUID = -4588805142596251105L;
	private CommunicateeService communicateeService;
	private List<Communicatee> datasource;

	public LazyCommunicateeModel(CommunicateeService communicateeService) {
		super();
		this.communicateeService = communicateeService;
		this.setRowCount((int) communicateeService.count());
	}

	public LazyCommunicateeModel(List<Communicatee> datasource) {
		this.datasource = datasource;
		this.setRowCount(datasource.size());
		this.setPageSize(datasource.size());
	}

	public List<Communicatee> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (datasource == null)
			return fromDb(first, pageSize, sortField, sortOrder, filters);
		else
			return fromResult(first, pageSize, sortField, sortOrder, filters);
	}

	private List<Communicatee> fromResult(int first, int pageSize,
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

	private List<Communicatee> fromDb(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, String> filters) {
		List<Communicatee> data = Lists.newArrayList();
		PageRequest pageRequest = new PageRequest(first / pageSize, pageSize);
		data = communicateeService.findAll(pageRequest);
		return data;
	}
}
