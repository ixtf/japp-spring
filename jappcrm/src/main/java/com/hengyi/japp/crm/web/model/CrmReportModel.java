package com.hengyi.japp.crm.web.model;

import java.util.List;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Report;

public class CrmReportModel implements CrmReport {
	private final Crm crm;
	private final Report report;
	private List<CrmReportLine<?>> lines;
	private List<CrmReportLine<CrmField>> crmFieldLines;
	private List<CrmReportLineIndicator> indicatorLines;

	public CrmReportModel(Crm crm, Report report) {
		super();
		this.crm = crm;
		this.report = report;
	}

	public Crm getCrm() {
		return crm;
	}

	public Report getReport() {
		return report;
	}

	public List<CrmReportLine<?>> getLines() {
		if (lines == null)
			lines = Lists.newArrayList();
		return lines;
	}

	public List<CrmReportLine<CrmField>> getCrmFieldLines() {
		if (crmFieldLines == null)
			crmFieldLines = Lists.newArrayList();
		return crmFieldLines;
	}

	public List<CrmReportLineIndicator> getIndicatorLines() {
		if (indicatorLines == null)
			indicatorLines = Lists.newArrayList();
		return indicatorLines;
	}

	public void setLines(List<CrmReportLine<?>> lines) {
		this.lines = lines;
	}

	public void setCrmFieldLines(List<CrmReportLine<CrmField>> crmFieldLines) {
		this.crmFieldLines = crmFieldLines;
	}

	public void setIndicatorLines(List<CrmReportLineIndicator> indicatorLines) {
		this.indicatorLines = indicatorLines;
	}
}
