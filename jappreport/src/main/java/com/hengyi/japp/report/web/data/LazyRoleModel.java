package com.hengyi.japp.report.web.data;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.service.RoleService;

public class LazyRoleModel extends LazyDataModel<Role> {
	private static final long serialVersionUID = -3643294680833922852L;
	private RoleService reportService;
	private List<Role> datasource;

	public LazyRoleModel(RoleService roleService) {
		super();
		this.reportService = roleService;
		this.setRowCount((int) roleService.count());
	}

	public LazyRoleModel(List<Role> datasource) {
		this.datasource = datasource;
		this.setRowCount(datasource.size());
		this.setPageSize(datasource.size());
	}

	public List<Role> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (datasource == null)
			return fromDb(first, pageSize, sortField, sortOrder, filters);
		else
			return fromResult(first, pageSize, sortField, sortOrder, filters);
	}

	private List<Role> fromResult(int first, int pageSize, String sortField,
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

	private List<Role> fromDb(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Role> data = Lists.newArrayList();
		PageRequest pageRequest = new PageRequest(first / pageSize, pageSize);
		data = reportService.findAll(pageRequest);
		return data;
	}
}
