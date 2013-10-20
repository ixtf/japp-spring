package com.hengyi.japp.report.service.impl;

import javax.annotation.Resource;

import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.repository.ReportRepository;
import com.hengyi.japp.report.service.ReportService;

public abstract class ReportServiceImpl<T extends Report> extends
		AbstractCommonCrudNeo4jService<T> implements ReportService<T> {
	@Resource
	private ReportRepository reportRepository;

}
