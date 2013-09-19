package com.hengyi.japp.crm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hengyi.japp.crm.domain.Communicatee;

public interface CommunicateeService {
	Communicatee findOne(Long nodeId);

	void save(Communicatee communicatee) throws Exception;

	void delete(Communicatee communicatee) throws Exception;

	List<Communicatee> findAll(Pageable pageRequest);

	List<Communicatee> findAllByQuery(String nameSearch) throws Exception;

	long count();
}
