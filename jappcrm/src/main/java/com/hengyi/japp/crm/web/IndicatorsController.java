package com.hengyi.japp.crm.web;

import java.util.List;

import com.hengyi.japp.crm.domain.Indicator;

public abstract class IndicatorsController extends AbstractController {
	private List<Indicator> indicators;
	private Indicator indicator;

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
}
