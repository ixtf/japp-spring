package com.hengyi.japp.crm.web;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.report.Report;
import com.hengyi.japp.crm.service.CrmReportService;
import com.hengyi.japp.crm.web.model.CrmReportModel;

@Named
@Scope("view")
public class CrmReportController extends AbstractController {
	@Inject
	private CrmReportService crmReportService;
	private Long crmNodeId;
	private Long reportNodeId;
	private CrmReportModel crmReport;

	public CrmReportModel getCrmReport() {
		if (crmReport == null)
			crmReportService.findOne(getCrmNodeId(), getReportNodeId());
		return crmReport;
	}

	public Crm getCrm() {
		return getCrmReport().getCrm();
	}

	public Report getReport() {
		return getCrmReport().getReport();
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
