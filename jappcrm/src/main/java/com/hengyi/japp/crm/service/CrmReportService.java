package com.hengyi.japp.crm.service;

import com.hengyi.japp.crm.data.CrmReport;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.web.model.ReportModel;

public interface CrmReportService {
	CrmReport findOne(Long crmNodeId, Long reportNodeId);

	Crm findOneCrm(Long crmNodeId);

	Report findOneReport(Long reportNodeId);

	ReportModel<CrmField> findOneCrmFieldReport(Long crmNodeId,
			Long reportNodeId);

	ReportModel<CrmField> findOneCrmFieldReport(Crm crm, Report report);

	ReportModel<Indicator> findOneIndicatorReport(Long crmNodeId,
			Long reportNodeId);

	ReportModel<Indicator> findOneIndicatorReport(Crm crm, Report report);
}
