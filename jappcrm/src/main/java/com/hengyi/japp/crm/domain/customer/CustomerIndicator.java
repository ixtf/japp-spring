package com.hengyi.japp.crm.domain.customer;

import java.util.List;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Indicator;

@NodeEntity
public class CustomerIndicator extends Indicator {
	private static final long serialVersionUID = 1470516342958682889L;

	public CustomerIndicator() {
		super();
	}

	public CustomerIndicator(String name, double percent) {
		super(name, percent);
	}

	public static final List<CustomerIndicator> getCalculateIndicators() {
		List<CustomerIndicator> result = Lists.newArrayList();
		result.add(new CustomerIndicatorSaleIncome());
		result.add(new CustomerIndicatorDurationYears());
		result.add(new CustomerIndicatorRegisterCapcital());
		return result;
	}
}
