package com.hengyi.japp.crm.web;

import java.util.List;

import javax.annotation.PostConstruct;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.service.IndicatorService;

public abstract class IndicatorsController<T extends Indicator> extends
		AbstractController {
	private IndicatorService<T> indicatorService;
	private List<T> indicators;
	private T indicator;

	@PostConstruct
	private void init() {
		indicatorService = getIndicatorService();
		indicators = indicatorService.findAll();
	}

	protected abstract IndicatorService<T> getIndicatorService();

	public void edit() {
		redirect(indicatorService.getUpdatePath(getIndicator().getNodeId()));
	}

	public void delete() {
		try {
			indicatorService.delete(getIndicator());
			indicators.remove(indicator);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public List<T> getIndicators() {
		return indicators;
	}

	public T getIndicator() {
		return indicator;
	}

	public void setIndicators(List<T> indicators) {
		this.indicators = indicators;
	}

	public void setIndicator(T indicator) {
		this.indicator = indicator;
	}
}
