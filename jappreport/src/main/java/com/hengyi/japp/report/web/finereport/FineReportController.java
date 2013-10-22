package com.hengyi.japp.report.web.finereport;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.service.ReportService;
import com.hengyi.japp.report.web.ReportController;

@Named
@Scope("view")
public class FineReportController extends ReportController<FineReport>
		implements Serializable {
	private static final long serialVersionUID = 3907353471779949405L;

	@Override
	protected ReportService<FineReport> getReportService() {
		return reportFactory.reportService(FineReport.class);
	}
}
