package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public interface IndicatorService<T extends Indicator> extends
		CommonCrudNeo4jService<T> {
	T newIndicator();

	void save(T indicator, Iterable<IndicatorValueScore> indicatorValueScores)
			throws Exception;
}
