package com.hengyi.japp.crm.service;

import java.io.Serializable;
import java.util.List;

import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public interface IndicatorService<T extends Indicator> extends
		CommonUrlService<Long>, Serializable {
	T newIndicator();

	T findOne(Long nodeId);

	void save(T indicator, Iterable<IndicatorValueScore> indicatorValueScores)
			throws Exception;

	void delete(T indicator) throws Exception;

	List<T> findAll();
}
