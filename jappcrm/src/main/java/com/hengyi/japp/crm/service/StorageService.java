package com.hengyi.japp.crm.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.storage.Storage;

public interface StorageService {
	Storage findOne(Long nodeId);

	List<Storage> findAll(PageRequest pageRequest);

	long count();

	List<Storage> findAllByQuery(String nameSearch) throws Exception;

	List<Indicator> findAllIndicator();
}
