package com.hengyi.japp.report.web;

import javax.annotation.Resource;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.ReportParam;
import com.hengyi.japp.report.domain.repository.ReportParamRepository;

public abstract class ReportParamController extends AbstractController {
	@Resource
	private ReportParamRepository reportParamRepository;
	private Long reportNodeId;
	private Report report;
	private ReportParam param;

	public Long getReportNodeId() {
		return reportNodeId;
	}

	public Report getReport() {
		return report;
	}

	public ReportParam getParam() {
		return param;
	}

	public void setReportNodeId(Long reportNodeId) {
		this.reportNodeId = reportNodeId;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public void setParam(ReportParam param) {
		this.param = param;
	}
}
