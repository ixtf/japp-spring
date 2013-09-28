package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.repository.IndicatorValueRepository;
import com.hengyi.japp.crm.service.IndicatorValueService;

@Named("indicatorValueService")
@Transactional
public class IndicatorValueServiceImp extends CommonUrlServiceImpl<Long>
		implements IndicatorValueService {
	@Resource
	private IndicatorValueRepository indicatorValueRepository;

	@Override
	public IndicatorValue findOne(Long nodeId) {
		return indicatorValueRepository.findOne(nodeId);
	}

	@Override
	public void delete(IndicatorValue indicatorValue) throws Exception {
		indicatorValueRepository.delete(indicatorValue);
	}

	@Override
	public List<IndicatorValue> findAll() {
		return Lists.newArrayList(indicatorValueRepository.findAll());
	}

	@Override
	public void save(IndicatorValue indicatorValue) {
		indicatorValueRepository.save(indicatorValue);
	}

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
}
