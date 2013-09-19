package com.hengyi.japp.crm.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.crm.domain.Bug;

public interface BugService {
	Bug findOne(Long nodeId);

	void save(Bug bug);

	void delete(Bug bug);

	List<Bug> findAll(PageRequest pageRequest);

	long count();

	List<Bug> findAllByQuery(String nameSearch);
}
