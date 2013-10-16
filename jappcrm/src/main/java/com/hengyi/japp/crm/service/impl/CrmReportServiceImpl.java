package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.data.CrmReport;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.ReportRepository;
import com.hengyi.japp.crm.service.CrmReportService;
import com.hengyi.japp.crm.web.model.CrmReportLineModel;
import com.hengyi.japp.crm.web.model.ReportModel;

@Named
@Transactional
public class CrmReportServiceImpl implements CrmReportService {
	@Resource
	protected Neo4jOperations template;
	@Resource
	protected CrmRepository crmRepository;
	@Resource
	protected ReportRepository reportRepository;

	@Override
	public ReportModel<CrmField> findOneCrmFieldReport(Crm crm, Report report) {
		ReportModel<CrmField> result = new ReportModel<>();
		List<CrmReportLineModel<CrmField>> lines = Lists.newArrayList();
		result.setName(report.getName());
		for (CrmReportLineModel crmField : report.getCrmFields()) {
			lines.add(crmField);
		}
		result.setLines(lines);
		return result;
	}

	@Override
	public ReportModel<Indicator> findOneIndicatorReport(Crm crm, Report report) {
		ReportModel<Indicator> result = new ReportModel<>();
		result.setName(report.getName());
		return result;
	}

	@Override
	public CrmReport findOne(Long crmNodeId, Long reportNodeId) {
		Crm crm = crmRepository.findOne(crmNodeId);
		Report report = reportRepository.findOne(reportNodeId);
		crm.getIndicatorScores(template);
		return new CrmReport(crm, report);
	}

	@Override
	public Crm findOneCrm(Long crmNodeId) {
		return crmRepository.findOne(crmNodeId);
	}

	@Override
	public Report findOneReport(Long reportNodeId) {
		return reportRepository.findOne(reportNodeId);
	}

	@Override
	public ReportModel<CrmField> findOneCrmFieldReport(Long crmNodeId,
			Long reportNodeId) {
		return findOneCrmFieldReport(findOneCrm(crmNodeId),
				findOneReport(reportNodeId));
	}

	@Override
	public ReportModel<Indicator> findOneIndicatorReport(Long crmNodeId,
			Long reportNodeId) {
		return findOneIndicatorReport(findOneCrm(crmNodeId),
				findOneReport(reportNodeId));
	}
}
