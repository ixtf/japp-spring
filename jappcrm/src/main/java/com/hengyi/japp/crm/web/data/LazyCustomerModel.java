package com.hengyi.japp.crm.web.data;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.service.CustomerService;

public class LazyCustomerModel extends LazyDataModel<Customer> {
	private static final long serialVersionUID = -4588805142596251105L;
	private CustomerService customerService;
	private List<Customer> datasource;

	public LazyCustomerModel(CustomerService customerService) {
		super();
		this.customerService = customerService;
		this.setRowCount((int) customerService.count());
	}

	public LazyCustomerModel(List<Customer> datasource) {
		this.datasource = datasource;
		this.setRowCount(datasource.size());
		this.setPageSize(datasource.size());
	}

	public List<Customer> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (datasource == null)
			return fromDb(first, pageSize, sortField, sortOrder, filters);
		else
			return fromResult(first, pageSize, sortField, sortOrder, filters);
	}

	private List<Customer> fromResult(int first, int pageSize,
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

	private List<Customer> fromDb(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Customer> data = Lists.newArrayList();
		PageRequest pageRequest = new PageRequest(first / pageSize, pageSize);
		data = customerService.findAll(pageRequest);
		return data;
	}
}
