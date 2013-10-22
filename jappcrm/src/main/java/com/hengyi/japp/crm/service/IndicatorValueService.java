package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.IndicatorValue;

public interface IndicatorValueService extends
		CommonCrudNeo4jService<IndicatorValue> {
	List<IndicatorValue> findAllByQuery(String nameSearch);
}
