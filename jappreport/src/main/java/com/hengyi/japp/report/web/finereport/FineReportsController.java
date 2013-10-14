package com.hengyi.japp.report.web.finereport;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.domain.finereport.FineReport;
import com.hengyi.japp.report.service.ReportService;
import com.hengyi.japp.report.service.finereport.FineReportService;
import com.hengyi.japp.report.web.ReportsController;

@Named
@Scope("view")
public class FineReportsController extends ReportsController<FineReport>
		implements Serializable {
	private static final long serialVersionUID = -5798948962908784416L;
	@Inject
	private FineReportService fineReportService;

	@Override
	protected ReportService<FineReport> getReportService() {
		return fineReportService;
	}
}
