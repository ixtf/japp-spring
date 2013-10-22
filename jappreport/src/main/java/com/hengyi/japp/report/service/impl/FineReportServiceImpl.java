package com.hengyi.japp.report.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.domain.repository.FineReportRepository;
import com.hengyi.japp.report.service.finereport.FineReportService;

@Named("fineReportService")
@Transactional
@SuppressWarnings("unchecked")
public class FineReportServiceImpl extends ReportServiceImpl<FineReport>
		implements FineReportService, Serializable {
	private static final long serialVersionUID = 8175817014790233532L;
	@Resource
	private FineReportRepository fineReportRepository;
	@Resource(name = "deployProperties")
	private Properties deployProperties;

	@Override
	public List<FineReport> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(fineReportRepository.findAllByQuery(
				FineReport.class.getSimpleName(), "name", nameSearch));
	}

	@Override
	public String getNewPath() {
		return "/admin/fineReport";
	}

	@Override
	public FineReport newReport() {
		return new FineReport();
	}

	@Override
	public String getUrl(FineReport report) {
		return deployProperties.getProperty("fineReportlet")
				+ report.getParms();
	}

	@Override
	public <R extends Repository<FineReport, Long>> R getRepository() {
		return (R) fineReportRepository;
	}

	@Override
	public void save(FineReport report, Menu menu) throws Exception {
		report.setMenu(menu);
		save(report);
	}
}
