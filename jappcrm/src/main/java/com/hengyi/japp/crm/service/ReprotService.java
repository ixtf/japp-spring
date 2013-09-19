package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.crm.domain.report.Report;

public interface ReprotService {
	Report findOne(Long nodeId);

	void save(Report report);

	void delete(Report report);

	List<Report> findAll();
}
