package com.hengyi.japp.trans.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.trans.domain.ys.YsDelivery;

public interface YSDeliveryService {
	YsDelivery findOne(String uuid);

	void save(YsDelivery ysDelivery);

	void delete(String uuid) throws Exception;

	void delete(YsDelivery ysDelivery) throws Exception;

	List<YsDelivery> findAll(PageRequest pageRequest);

	long count();
}
