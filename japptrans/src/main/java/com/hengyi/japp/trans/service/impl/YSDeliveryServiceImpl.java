package com.hengyi.japp.trans.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.trans.domain.repository.YSDeliveryRepository;
import com.hengyi.japp.trans.domain.ys.YsDelivery;
import com.hengyi.japp.trans.service.CacheService;
import com.hengyi.japp.trans.service.RecordService;
import com.hengyi.japp.trans.service.YSDeliveryService;

@Named
@Transactional
public class YSDeliveryServiceImpl implements YSDeliveryService {
	@Inject
	private CacheService cacheService;
	@Inject
	private RecordService recordService;
	@Inject
	private YSDeliveryRepository ysDeliveryRepository;

	@Override
	public void save(YsDelivery ysDelivery) {
		checkSave(ysDelivery);
		ysDeliveryRepository.save(ysDelivery);
	}

	private void checkSave(YsDelivery ysDelivery) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(String uuid) throws Exception {
		checkDelete(uuid);
		YsDelivery ysDelivery = findOne(uuid);
		ysDelivery.setDeleteFlag(true);
		ysDelivery.setOperator(cacheService.getCurrentOperator());
		ysDeliveryRepository.save(ysDelivery);
	}

	private void checkDelete(String uuid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(YsDelivery ysDelivery) throws Exception {
		delete(ysDelivery.getUuid());
	}

	@Override
	public YsDelivery findOne(String uuid) {
		return ysDeliveryRepository.findOne(uuid);
	}

	@Override
	public List<YsDelivery> findAll(PageRequest pageRequest) {
		return Lists.newArrayList(ysDeliveryRepository.findAll(pageRequest));
	}

	@Override
	public long count() {
		return ysDeliveryRepository.count();
	}
}
