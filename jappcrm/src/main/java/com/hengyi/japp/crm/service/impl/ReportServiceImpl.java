package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.domain.repository.CustomerReportRepository;
import com.hengyi.japp.crm.domain.repository.ReportRepository;
import com.hengyi.japp.crm.domain.repository.StorageReportRepository;
import com.hengyi.japp.crm.service.ReportService;

public abstract class ReportServiceImpl<T extends Report> implements
		ReportService<T> {
	@Resource
	protected ReportRepository reportRepository;
	@Resource
	protected CustomerReportRepository customerReportRepository;
	@Resource
	protected StorageReportRepository storageReportRepository;

	@Override
	public void save(T report, Iterable<Indicator> indicators) {
		report.setIndicators(indicators);
		reportRepository.save(report);
	}

	@Override
	public void delete(T report) {
		reportRepository.delete(report);
	}
}
