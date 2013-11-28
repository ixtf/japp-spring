package com.hengyi.japp.report.domain.finereport;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.ReportType;

//lin0878950
@NodeEntity
public class FineReport extends Report {
	private static final long serialVersionUID = -5182667801659880331L;

	@Override
	public ReportType getReportType() {
		return ReportType.FINEREPORT;
	}
}
