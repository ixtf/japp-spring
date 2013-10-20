package com.hengyi.japp.crm.web.model;

import java.util.Locale;

import com.hengyi.japp.crm.domain.Indicator;

public class CrmReportLineIndicatorModel extends AbstractReportLine<Indicator>
		implements CrmReportLineIndicator {
	private Object value;
	private Double score;

	public CrmReportLineIndicatorModel(CrmReportModel crmReport, Indicator data) {
		super(crmReport, data);
	}

	@Override
	public String getName() {
		return getData().getName();
	}

	@Override
	public String getName(Locale locale) {
		return getName();
	}

	@Override
	public Object getValue() {
		if (value == null)
			value = getData().getIndicatorValues(getCrm());
		return value;
	}

	@Override
	public double getPercent() {
		return getData().getPercent();
	}

	@Override
	public double getScore() {
		if (score == null)
			score = getData().calculateScore(getCrm());
		return score;
	}

	@Override
	public double getScorePercent() {
		return getData().calculateScorePercent(getCrm());
	}
}
