package com.hengyi.japp.crm.service;

import java.io.Serializable;

import com.hengyi.japp.crm.data.CrmReport;

public interface CrmReportService extends Serializable {
	CrmReport findOne(Long crmNodeId, Long reportNodeId);
}
