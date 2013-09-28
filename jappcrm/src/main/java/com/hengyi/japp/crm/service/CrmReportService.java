package com.hengyi.japp.crm.service;

import com.hengyi.japp.crm.data.CrmReport;

public interface CrmReportService {
	CrmReport findOne(Long crmNodeId, Long reportNodeId);
}
