package com.hengyi.japp.report.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.report.domain.Report;

public interface ReportService<T extends Report> extends
		CommonUrlService<Long>, Serializable {
	T findOne(Long nodeId);

	T newReport();

	void save(T report);

	void delete(T report);

	void delete(Long nodeId);

	long count();

	List<T> findAll(PageRequest pageRequest);

	List<T> findAllByQuery(String nameSearch) throws Exception;

	String getUrl(T report);
}
