package com.hengyi.japp.crm.exception;

import java.util.List;

import com.hengyi.japp.crm.ErrorUtil;
import com.hengyi.japp.crm.domain.Indicator;

public class NoIndicatorValueException extends Exception {
	private static final long serialVersionUID = 5494945099785702307L;
	private final List<Indicator> indicators;

	public NoIndicatorValueException(List<Indicator> indicators) {
		super();
		this.indicators = indicators;
	}

	@Override
	public String getMessage() {
		return ErrorUtil.noIndicatorValue(indicators);
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}
}
