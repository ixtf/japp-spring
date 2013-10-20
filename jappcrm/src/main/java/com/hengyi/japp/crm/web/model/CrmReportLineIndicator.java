package com.hengyi.japp.crm.web.model;

import com.hengyi.japp.crm.domain.Indicator;

public interface CrmReportLineIndicator extends CrmReportLine<Indicator> {
	double getPercent();

	double getScore();

	double getScorePercent();
}
