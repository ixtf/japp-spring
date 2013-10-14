package com.hengyi.japp.report.web.finereport;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.service.ReportService;
import com.hengyi.japp.report.service.finereport.FineReportService;
import com.hengyi.japp.report.web.ReportController;

@Named
@Scope("view")
public class FineReportController extends ReportController<FineReport>
		implements Serializable {
	private static final long serialVersionUID = 3907353471779949405L;
	private static final String URL = "http://192.168.17.51:8075/WebReport/ReportServer?reportlet=";
	@Inject
	private FineReportService fineReportService;

	@Override
	protected ReportService<FineReport> getReportService() {
		return fineReportService;
	}

	@Override
	protected String getUrl(FineReport report) {
		return URL + report.getCpt();
	}
}
