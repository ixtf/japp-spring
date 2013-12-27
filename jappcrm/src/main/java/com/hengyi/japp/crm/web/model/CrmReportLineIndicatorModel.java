package com.hengyi.japp.crm.web.model;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorScore;

public class CrmReportLineIndicatorModel implements CrmReportLineIndicator {
	private final Indicator indicator;
	private final IndicatorScore indicatorScore;
	// 没有计算权重的分数
	private final Double score;
	private final Object value;

	public CrmReportLineIndicatorModel(CrmReport crmReport,
			Indicator indicator, IndicatorScore indicatorScore) {
		super();
		this.indicator = indicator;
		this.indicatorScore = indicatorScore;
		score = indicator.calculateScore(crmReport.getCrm());
		// TODO 修复错误
		value = crmReport.getCrm().getIndicatorValues();
	}

	@Override
	public String getName() {
		return getData().getName();
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Indicator getData() {
		return indicator;
	}

	@Override
	public Double getScore() {
		return score;
	}

	@Override
	public Double getScorePercent() {
		return indicatorScore.getScore();
	}

}
