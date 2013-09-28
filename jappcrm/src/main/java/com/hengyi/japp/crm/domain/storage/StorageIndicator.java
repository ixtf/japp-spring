package com.hengyi.japp.crm.domain.storage;

import java.util.List;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Indicator;

@NodeEntity
public class StorageIndicator extends Indicator {
	private static final long serialVersionUID = 8640666656378277929L;

	public static final List<StorageIndicator> getCalculateIndicators() {
		List<StorageIndicator> result = Lists.newArrayList();
		result.add(new StorageIndicatorDurationYears());
		result.add(new StorageIndicatorRegisterCapcital());
		result.add(new StorageIndicatorCapcital());
		return result;
	}

	public StorageIndicator() {
		super();
	}

	public StorageIndicator(String name, double percent) {
		super(name, percent);
	}
}
