package com.hengyi.japp.crm.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorScore;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public class CrmReportIndicator implements Serializable {
	private static final long serialVersionUID = -409108097344270263L;
	private final CrmReport crmReport;
	private final IndicatorScore indicatorScore;
	private final List<IndicatorValue> indicatorValues;
	// 指标的得分，不×权重
	private final double score;

	public static List<CrmReportIndicator> crmReportIndicators(
			CrmReport crmReport) {
		ImmutableList.Builder<CrmReportIndicator> builder = ImmutableList
				.builder();
		Set<Indicator> indicators = ImmutableSet.copyOf(crmReport.getReport()
				.getIndicators());
		for (IndicatorScore indicatorScore : crmReport.getCrm()
				.getIndicatorScores())
			if (indicators.contains(indicatorScore.getEnd()))
				builder.add(new CrmReportIndicator(crmReport, indicatorScore));
		return builder.build();
	}

	public CrmReportIndicator(CrmReport crmReport, IndicatorScore indicatorScore) {
		super();
		this.crmReport = crmReport;
		this.indicatorScore = indicatorScore;
		this.indicatorValues = initIndicatorValues();
		this.score = indicatorScore.getEnd().calculateScore(crmReport.getCrm());
	}

	private List<IndicatorValue> initIndicatorValues() {
		ImmutableList.Builder<IndicatorValue> builder = ImmutableList.builder();
		Set<IndicatorValue> crmIndicatorValues = Sets.newHashSet(crmReport
				.getCrm().getIndicatorValues());
		for (IndicatorValueScore indicatorValueScore : indicatorScore.getEnd()
				.getIndicatorValueScores()) {
			IndicatorValue indicatorValue = indicatorValueScore.getEnd();
			if (crmIndicatorValues.contains(indicatorValue))
				builder.add(indicatorValue);
		}
		return builder.build();
	}

	public List<IndicatorValue> getIndicatorValues() {
		return indicatorValues;
	}

	public IndicatorScore getIndicatorScore() {
		return indicatorScore;
	}

	public double getScore() {
		return score;
	}
}
