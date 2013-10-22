package com.hengyi.japp.crm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.data.CrmFieldType;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CrmField;
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
@SuppressWarnings("unchecked")
public class StorageServiceImpl extends CrmServiceImpl<Storage> implements
		StorageService {
	@Resource
	private StorageRepository storageRepository;
	@Resource
	private StorageIndicatorRepository storageIndicatorRepository;

	@Override
	public List<Storage> findAllByQuery(String nameSearch) throws Exception {
		MyUtil.checkSearch(nameSearch);
		return Lists.newArrayList(storageRepository.findAllByQuery("name",
				nameSearch));
	}

	@Override
	public void save(Storage crm,
			Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			Iterable<CrmType> crmTypes, Iterable<Certificate> certificates,
			Communicatee communicatee, Iterable<Communicatee> communicatees,
			Iterable<Associate> associates) throws Exception {
		super.save(crm, indicatorMap, crmTypes, certificates, communicatee,
				communicatees, associates);

		final StorageUpdateEvent event = new StorageUpdateEvent(crm.getNodeId());
		if (TransactionSynchronizationManager.isActualTransactionActive())
			TransactionSynchronizationManager
					.registerSynchronization(new TransactionSynchronizationAdapter() {
						@Override
						public void afterCommit() {
							eventPublisher.publish(event);
						}
					});
		else
			eventPublisher.publish(event);
	}

	@Override
	public List<Indicator> findAllIndicator() {
		List<Indicator> result = Lists.newArrayList();
		for (StorageIndicator indicator : storageIndicatorRepository.findAll())
			result.add(indicator);
		return result;
	}

	@Override
	public List<CrmField> findAllCrmField() {
		List<CrmField> result = findAllCrmField(CrmFieldType.CRM);
		result.addAll(findAllCrmField(CrmFieldType.STORAGE));
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

	@Override
	public <R extends Repository<Storage, Long>> R getRepository() {
		return (R) storageRepository;
	}
}
