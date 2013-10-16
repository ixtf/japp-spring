package com.hengyi.japp.crm.web.model;

import java.util.List;

public class ReportModel<LINE_VALUE> {
	private String name;
	private List<CrmReportLineModel<LINE_VALUE>> lines;

	public String getName() {
		return name;
	}

	public List<CrmReportLineModel<LINE_VALUE>> getLines() {
		return lines;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLines(List<CrmReportLineModel<LINE_VALUE>> lines) {
		this.lines = lines;
	}
}
