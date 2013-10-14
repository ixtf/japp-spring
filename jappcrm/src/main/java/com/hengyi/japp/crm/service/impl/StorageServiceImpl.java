package com.hengyi.japp.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.repository.StorageIndicatorRepository;
import com.hengyi.japp.crm.domain.repository.StorageRepository;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.event.storage.StorageUpdateEvent;
import com.hengyi.japp.crm.service.storage.StorageService;

@Named("storageService")
@Transactional
public class StorageServiceImpl extends CrmServiceImpl<Storage> implements
		StorageService {
	@Resource
	private StorageRepository storageRepository;
	@Resource
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
	public void save(Storage crm,
			Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			CrmType crmType, Iterable<Certificate> certificates,
			Communicatee communicatee, Iterable<Communicatee> communicatees,
			Iterable<Associate> associates) throws Exception {
		super.save(crm, indicatorMap, crmType, certificates, communicatee,
				communicatees, associates);
		eventPublisher.publish(new StorageUpdateEvent(crm.getNodeId()));
	}

	@Override
	public List<Indicator> findAllIndicator() {
		List<Indicator> result = Lists.newArrayList();
		for (StorageIndicator indicator : storageIndicatorRepository.findAll())
			result.add(indicator);
		return result;
	}

	@Override
	public Storage newCrm() {
		return new Storage();
	}

	@Override
	public String getNewPath() {
		return "/storage";
	}
}
