package com.hengyi.japp.crm.service;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.web.model.CrmReport;

public interface CrmReportService {
	CrmReport findOne(Long crmNodeId, Long reportNodeId);

	Crm findOneCrm(Long crmNodeId);

	Report findOneReport(Long reportNodeId);
}
