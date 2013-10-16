package com.hengyi.japp.crm.web;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.data.CrmReport;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.service.CrmReportService;
import com.hengyi.japp.crm.web.model.ReportModel;

@Named
@Scope("view")
public class CrmReportController extends AbstractController {
	@Inject
	private CrmReportService crmReportService;
	private Long crmNodeId;
	private Long reportNodeId;
	private Crm crm;
	private Report report;
	private CrmReport crmReport;
	private ReportModel<CrmField> crmFieldReport;
	private ReportModel<Indicator> indicatorReport;

	public ReportModel<CrmField> getCrmFieldReport() {
		if (crmFieldReport == null)
			crmFieldReport = crmReportService.findOneCrmFieldReport(
					getCrmNodeId(), getReportNodeId());
		return crmFieldReport;
	}

	public ReportModel<Indicator> getIndicatorReport() {
		if (indicatorReport == null)
			indicatorReport = crmReportService.findOneIndicatorReport(
					getCrmNodeId(), getReportNodeId());
		return indicatorReport;
	}

	public CrmReport getCrmReport() {
		if (crmReport == null)
			crmReport = crmReportService.findOne(crmNodeId, reportNodeId);
		return crmReport;
	}

	public Crm getCrm() {
		if (crm == null)
			crm = crmReportService.findOneCrm(crmNodeId);
		return crm;
	}

	public Report getReport() {
		if (report == null)
			report = crmReportService.findOneReport(reportNodeId);
		return report;
	}

	public void setCrm(Crm crm) {
		this.crm = crm;
	}

	public void setReport(Report report) {
		this.report = report;
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
