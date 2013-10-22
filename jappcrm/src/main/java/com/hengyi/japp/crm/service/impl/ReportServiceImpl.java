package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;

import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.domain.repository.ReportRepository;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.ReportService;

public abstract class ReportServiceImpl<T extends Report> extends
		CommonUrlServiceImpl<Long> implements ReportService<T> {
	@Resource
	protected ReportRepository reportRepository;

	@Override
	public void save(T report, Iterable<Indicator> indicators) {
		report.setIndicators(indicators);
		reportRepository.save(report);
	}

	@Override
	public void delete(T report) {
		reportRepository.delete(report);
	}

	@Override
	public String getNewPath() {
		return getCrmService().getNewPath() + "/report";
	}

	protected abstract CrmService<? extends Crm> getCrmService();
}