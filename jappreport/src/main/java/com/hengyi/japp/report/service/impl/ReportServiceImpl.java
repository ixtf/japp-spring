package com.hengyi.japp.report.service.impl;

import javax.annotation.Resource;

import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.ReportService;

public abstract class ReportServiceImpl<T extends Report> extends
		CommonUrlServiceImpl<Long> implements ReportService<T> {
	private static final long serialVersionUID = 7461162266346111835L;
	@Resource
	private ReportRepository reportRepository;

	@Override
	public void save(T report) {
		reportRepository.save(report);
	}

	@Override
	public void delete(T report) {
		reportRepository.delete(report);
	}

	@Override
	public void delete(Long nodeId) {
		reportRepository.delete(nodeId);
	}

}
