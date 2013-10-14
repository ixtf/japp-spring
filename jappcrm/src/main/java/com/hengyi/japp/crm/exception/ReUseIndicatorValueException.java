package com.hengyi.japp.crm.exception;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.hengyi.japp.crm.ErrorUtil;
import com.hengyi.japp.crm.domain.IndicatorValue;

public class ReUseIndicatorValueException extends Exception {
	private static final long serialVersionUID = 5494945099785702307L;
	private final Set<IndicatorValue> indicatorValues;

	public ReUseIndicatorValueException(Iterable<IndicatorValue> indicatorValues) {
		super();
		this.indicatorValues = ImmutableSet.copyOf(indicatorValues);
	}

	@Override
	public String getMessage() {
		return ErrorUtil.reUseIndicatorValue(indicatorValues);
	}

	public Set<IndicatorValue> getIndicatorValues() {
		return indicatorValues;
	}
}
