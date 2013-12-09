package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.repository.IndicatorValueRepository;
import com.hengyi.japp.crm.service.IndicatorValueService;

@Named("indicatorValueService")
@Transactional
@SuppressWarnings("unchecked")
public class IndicatorValueServiceImp extends
		AbstractCommonCrudNeo4jService<IndicatorValue> implements
		IndicatorValueService {
	@Resource
	private IndicatorValueRepository indicatorValueRepository;

	@Override
	public List<IndicatorValue> findAllByQuery(String nameSearch) {
		nameSearch = StringUtils.trimToEmpty(nameSearch);
		return Lists.newArrayList(indicatorValueRepository.findAllByQuery(
				"name", nameSearch));
	}

	@Override
	public String getNewPath() {
		return "/indicatorValue";
	}

	@Override
	public <R extends Repository<IndicatorValue, Long>> R getRepository() {
		return (R) indicatorValueRepository;
	}

	// @Override
	// public void save(IndicatorValue indicatorValue) {
	// indicatorValueRepository.save(indicatorValue);
	// }
}
