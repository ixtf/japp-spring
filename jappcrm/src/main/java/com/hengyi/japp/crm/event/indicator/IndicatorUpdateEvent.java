package com.hengyi.japp.crm.event.indicator;

import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.domain.Indicator;

public class IndicatorUpdateEvent extends ApplicationEvent {
	private static final long serialVersionUID = -3942193835269289143L;

	public IndicatorUpdateEvent(Indicator indicator) {
		super(indicator);
	}
}
