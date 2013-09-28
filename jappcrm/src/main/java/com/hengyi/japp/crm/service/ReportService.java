package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;

public interface ReportService<T extends Report> {
	T findOne(Long nodeId);

	void save(T report, Iterable<Indicator> indicators);

	void delete(T report);

	List<T> findAll();
}
