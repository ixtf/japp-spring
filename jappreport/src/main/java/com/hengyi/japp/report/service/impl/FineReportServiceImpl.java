package com.hengyi.japp.report.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.report.MyUtil;
import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.domain.repository.FineReportRepository;
import com.hengyi.japp.report.service.finereport.FineReportService;

@Named("fineReportService")
@Transactional
public class FineReportServiceImpl extends ReportServiceImpl<FineReport>
		implements FineReportService {
	private static final long serialVersionUID = 8616574989379917977L;
	@Resource
	private FineReportRepository fineReportRepository;
	@Resource(name = "deployProperties")
	private Properties deployProperties;

	@Override
	public FineReport findOne(Long nodeId) {
		return fineReportRepository.findOne(nodeId);
	}

	@Override
	public long count() {
		return fineReportRepository.count();
	}

	@Override
	public List<FineReport> findAll(PageRequest pageRequest) {
		return Lists.newArrayList(fineReportRepository.findAll(pageRequest));
	}

	@Override
	public List<FineReport> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(fineReportRepository.findAllByQuery("name",
				nameSearch));
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
		return deployProperties.getProperty("fineReportlet") + report.getCpt();
	}
}
