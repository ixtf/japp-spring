package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.ReportRepository;
import com.hengyi.japp.crm.service.CrmReportService;
import com.hengyi.japp.crm.web.model.CrmReport;
import com.hengyi.japp.crm.web.model.CrmReportLineCrmFieldModel;
import com.hengyi.japp.crm.web.model.CrmReportLineIndicatorModel;
import com.hengyi.japp.crm.web.model.CrmReportModel;

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
	public CrmReport findOne(Long crmNodeId, Long reportNodeId) {
		Crm crm = crmRepository.findOne(crmNodeId);
		Report report = reportRepository.findOne(reportNodeId);
		CrmReportModel crmReport = new CrmReportModel(crm, report);
		for (CrmField crmField : report.getCrmFields()) {
			CrmReportLineCrmFieldModel line = new CrmReportLineCrmFieldModel(
					crmReport, crmField);
			crmReport.getCrmFieldLines().add(line);
			crmReport.getLines().add(line);
		}
		for (Indicator indicator : report.getIndicators()) {
			CrmReportLineIndicatorModel line = new CrmReportLineIndicatorModel(
					crmReport, indicator);
			crmReport.getIndicatorLines().add(line);
			crmReport.getLines().add(line);
		}
		return crmReport;
	}

	@Override
	public Crm findOneCrm(Long crmNodeId) {
		return crmRepository.findOne(crmNodeId);
	}

	@Override
	public Report findOneReport(Long reportNodeId) {
		return reportRepository.findOne(reportNodeId);
	}
}
