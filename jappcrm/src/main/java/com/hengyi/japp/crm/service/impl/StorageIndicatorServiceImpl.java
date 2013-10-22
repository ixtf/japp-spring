package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.repository.StorageIndicatorRepository;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.storage.StorageIndicatorService;
import com.hengyi.japp.crm.service.storage.StorageService;

@Named("storageIndicatorService")
@Transactional
@SuppressWarnings("unchecked")
public class StorageIndicatorServiceImpl extends
		IndicatorServiceImpl<StorageIndicator> implements
		StorageIndicatorService {
	@Inject
	private StorageService storageService;

	@Resource
	private StorageIndicatorRepository storageIndicatorRepository;

	@Override
	public StorageIndicator newIndicator() {
		return new StorageIndicator();
	}

	@Override
	public StorageIndicator findOne(Long nodeId) {
		return storageIndicatorRepository.findOne(nodeId);
	}

	@Override
	public List<StorageIndicator> findAll() {
		return Lists.newArrayList(storageIndicatorRepository.findAll());
	}

	@Override
	protected CrmService<?> getCrmService() {
		return storageService;
	}

	@Override
	public <R extends Repository<StorageIndicator, Long>> R getRepository() {
		return (R) storageIndicatorRepository;
	}
}
