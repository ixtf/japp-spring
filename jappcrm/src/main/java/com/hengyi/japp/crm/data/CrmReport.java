package com.hengyi.japp.crm.data;

import java.io.Serializable;
import java.util.List;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Report;

public class CrmReport implements Serializable {
	private static final long serialVersionUID = -409108097344270263L;
	private final Crm crm;
	private final Report report;
	private final List<CrmReportField> crmReportFields;
	private final List<CrmReportIndicator> crmReportIndicators;
	private final double totalIndicatorScore;

	public CrmReport(Crm crm, Report report) {
		super();
		this.crm = crm;
		this.report = report;
		this.crmReportFields = CrmReportField.crmReportFields(this);
		this.crmReportIndicators = CrmReportIndicator.crmReportIndicators(this);
		this.totalIndicatorScore = initTotalIndicatorScore();
	}

	private double initTotalIndicatorScore() {
		double result = 0;
		for (CrmReportIndicator crmReportIndicator : crmReportIndicators)
			result = result + crmReportIndicator.getIndicatorScore().getScore();
		return result;
	}

	public Crm getCrm() {
		return crm;
	}

	public Report getReport() {
		return report;
	}

	public List<CrmReportField> getCrmReportFields() {
		return crmReportFields;
	}

	public List<CrmReportIndicator> getCrmReportIndicators() {
		return crmReportIndicators;
	}

	public double getTotalIndicatorScore() {
		return totalIndicatorScore;
	}
}
