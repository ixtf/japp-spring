package com.hengyi.japp.crm.dto;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

public class IndicatorValueScoreDTO extends AbstractNeo4j implements
		Comparable<IndicatorValueScoreDTO> {
	private static final long serialVersionUID = 1L;
	private IndicatorValueDTO indicatorValue;
	private double score;

	public IndicatorValueDTO getIndicatorValue() {
		return indicatorValue;
	}

	public void setIndicatorValue(IndicatorValueDTO indicatorValue) {
		this.indicatorValue = indicatorValue;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public int compareTo(IndicatorValueScoreDTO o) {
		return o == null ? -1 : Double.compare(getScore(), o.getScore());
	}
}
