package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.crm.domain.IndicatorValue;

public interface IndicatorValueService extends CommonUrlService<Long> {
	IndicatorValue findOne(Long nodeId);

	void save(IndicatorValue indicatorValue);

	void delete(IndicatorValue indicatorValue) throws Exception;

	List<IndicatorValue> findAll();

	List<IndicatorValue> findAllByQuery(String nameSearch);
}
