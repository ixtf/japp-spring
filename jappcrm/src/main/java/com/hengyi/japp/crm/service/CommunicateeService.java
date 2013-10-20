package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.Communicatee;

public interface CommunicateeService extends
		CommonCrudNeo4jService<Communicatee> {
	void save(Communicatee communicatee) throws Exception;

	List<Communicatee> findAllByQuery(String nameSearch) throws Exception;
}
