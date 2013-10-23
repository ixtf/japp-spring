package com.hengyi.japp.crm.web.model;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Report;

public abstract class AbstractReportLine<T> {
	private final CrmReportModel crmReport;
	private final T data;

	public AbstractReportLine(CrmReportModel crmReport, T data) {
		super();
		this.crmReport = crmReport;
		this.data = data;
	}

	public Crm getCrm() {
		return crmReport.getCrm();
	}

	public Report getReport() {
		return crmReport.getReport();
	}

	public T getData() {
		return data;
	}
}
