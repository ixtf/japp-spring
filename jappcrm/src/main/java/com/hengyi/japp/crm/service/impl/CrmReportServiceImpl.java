package com.hengyi.japp.crm.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.data.CrmReport;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.ReportRepository;
import com.hengyi.japp.crm.service.CrmReportService;

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
		crm.getIndicatorScores(template);
		return new CrmReport(crm, report);
	}
}
