package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;

import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.domain.repository.ReportRepository;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.ReportService;

public abstract class ReportServiceImpl<T extends Report> extends
		AbstractCommonCrudNeo4jService<T> implements ReportService<T> {
	@Resource
	protected ReportRepository reportRepository;

	@Override
	public void save(T report, Iterable<Indicator> indicators,
			Iterable<CrmField> crmFields) {
		report.setIndicators(indicators);
		report.setCrmFields(crmFields);
		reportRepository.save(report);
	}

	@Override
	public String getNewPath() {
		return getCrmService().getNewPath() + "/report";
	}

	protected abstract CrmService<? extends Crm> getCrmService();
}
