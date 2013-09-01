package com.hengyi.japp.crm.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.repository.StorageIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.StorageRepository;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.service.StorageService;

@Named
@Transactional
public class StorageServiceImpl implements StorageService {
	@Inject
	private StorageRepository storageRepository;
	@Inject
	private StorageIndicatorRepository storageIndicatorRepository;

	@Override
	public Storage findOne(Long nodeId) {
		return storageRepository.findOne(nodeId);
	}

	@Override
	public void save(Storage storage) throws Exception {
		storageRepository.save(storage);
	}

	@Override
	public void delete(Storage storage) throws Exception {
		storageRepository.delete(storage);
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
	public List<Storage> findAllByQuery(String nameSearch) {
		return Lists.newArrayList(storageRepository.findAllByQuery("name",
				nameSearch));
	}

	@Override
	public List<StorageIndicator> findAllIndicator() {
		return Lists.newArrayList(storageIndicatorRepository.findAll());
	}
}
