package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.repository.StorageIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.StorageRepository;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.service.CacheService;
import com.hengyi.japp.crm.service.StorageService;

@Named
@Transactional
public class StorageServiceImpl implements StorageService {
	@Inject
	private Neo4jOperations template;
	// @Inject
	// private EventBus eventBus;
	@Inject
	private CacheService cacheServiceFacade;
	@Inject
	private StorageRepository storageRepository;
	@Inject
	private StorageIndicatorRepository storageIndicatorRepository;

	@Override
	public Storage findOne(Long nodeId) {
		return storageRepository.findOne(nodeId);
	}

	@Override
	public List<Storage> findAll(PageRequest pageRequest) {
		return Lists.newArrayList(storageRepository.findAll(pageRequest));
	}

	@Override
	public long count() {
		return storageRepository.count();
	}

	@Override
	public List<Storage> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(storageRepository.findAllByQuery("name",
				nameSearch));
	}

	@Override
	public List<Indicator> findAllIndicator() {
		List<Indicator> result = Lists.newArrayList();
		for (StorageIndicator indicator : storageIndicatorRepository.findAll())
			result.add(indicator);
		return result;
	}
}
