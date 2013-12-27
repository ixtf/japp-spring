package com.hengyi.japp.crm.web.model;

import com.hengyi.japp.crm.domain.Indicator;

public interface CrmReportLineIndicator extends CrmReportLine<Indicator> {
	/*
	 * @return 没有计算权重的分数
	 */
	Double getScore();

	Double getScorePercent();
}
