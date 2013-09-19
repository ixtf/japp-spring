package com.hengyi.japp.trans.web.data;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.hengyi.japp.trans.domain.ys.YsDelivery;
import com.hengyi.japp.trans.service.YSDeliveryService;

public class YsDeliveryModel extends LazyDataModel<YsDelivery> {
	private static final long serialVersionUID = -4588805142596251105L;
	private YSDeliveryService ysDeliveryService;
	private List<YsDelivery> datasource;

	public YsDeliveryModel(YSDeliveryService ysDeliveryService) {
		super();
		this.ysDeliveryService = ysDeliveryService;
		this.setRowCount((int) ysDeliveryService.count());
	}

	public YsDeliveryModel(List<YsDelivery> datasource) {
		this.datasource = datasource;
		this.setRowCount(datasource.size());
		this.setPageSize(datasource.size());
	}

	public List<YsDelivery> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		if (datasource == null)
			return fromDb(first, pageSize, sortField, sortOrder, filters);
		else
			return fromResult(first, pageSize, sortField, sortOrder, filters);
	}

	private List<YsDelivery> fromResult(int first, int pageSize,
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

	private List<YsDelivery> fromDb(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<YsDelivery> data = Lists.newArrayList();
		PageRequest pageRequest = new PageRequest(first / pageSize, pageSize);
		data = ysDeliveryService.findAll(pageRequest);
		return data;
	}
}
