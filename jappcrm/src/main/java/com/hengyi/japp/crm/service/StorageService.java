package com.hengyi.japp.crm.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.crm.domain.storage.Storage;

public interface StorageService {
	Storage findOne(Long nodeId);

	void save(Storage customer) throws Exception;

	void delete(Storage customer) throws Exception;

	List<Storage> findAll(PageRequest pageRequest);

	long count();

	List<Storage> findAllByQuery(String nameSearch);
}
