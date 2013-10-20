package com.hengyi.japp.report.service;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.finereport.FineReportService;

@Named
@Singleton
@SuppressWarnings("unchecked")
public class ReportServiceFactory implements Serializable {
	private static final long serialVersionUID = 2659543725551619057L;
	@Inject
	private FineReportService fineReportService;
	@Resource
	private ReportRepository reportRepository;

	public <T extends Report> ReportService<T> get(Class<T> clazz) {
		if (clazz == FineReport.class)
			return (ReportService<T>) fineReportService;
		else
			throw new RuntimeException("ReportService not found !");
	}

	public <T extends Report> ReportService<T> get(T report) {
		return (ReportService<T>) get(report.getClass());
	}

	public ReportService<? extends Report> get(Long reportNodeId) {
		return get(reportRepository.findOne(reportNodeId));
	}
}
