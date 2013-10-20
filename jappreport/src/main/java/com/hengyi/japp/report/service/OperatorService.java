package com.hengyi.japp.report.service;

import java.util.List;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.report.domain.Operator;

public interface OperatorService extends CommonCrudNeo4jService<Operator> {
	Operator findOne(String uuid) throws Exception;

	Operator findOne(UserDTO user) throws Exception;

	void save(Operator operator) throws Exception;

	void updateTheme(String uuid, String theme) throws Exception;

	List<Operator> findAllByQuery(String nameSearch) throws Exception;
}
