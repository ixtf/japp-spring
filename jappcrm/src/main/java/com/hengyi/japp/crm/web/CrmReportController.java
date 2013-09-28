package com.hengyi.japp.crm.web;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.data.CrmReport;
import com.hengyi.japp.crm.service.CrmReportService;

@Named
@Scope("view")
public class CrmReportController extends AbstractController {
	@Inject
	private CrmReportService crmReportService;
	private Long crmNodeId;
	private Long reportNodeId;
	private CrmReport crmReport;

	public CrmReport getCrmReport() {
		if (crmReport == null)
			crmReport = crmReportService.findOne(crmNodeId, reportNodeId);
		return crmReport;
	}

	public Long getCrmNodeId() {
		return crmNodeId;
	}

	public Long getReportNodeId() {
		return reportNodeId;
	}

	public void setCrmNodeId(Long crmNodeId) {
		this.crmNodeId = crmNodeId;
	}

	public void setReportNodeId(Long reportNodeId) {
		this.reportNodeId = reportNodeId;
	}
}
