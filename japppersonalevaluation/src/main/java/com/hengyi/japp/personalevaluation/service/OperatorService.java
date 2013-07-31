package com.hengyi.japp.personalevaluation.service;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.personalevaluation.domain.node.Operator;

public interface OperatorService {
	Operator findOne(Long nodeId) throws Exception;

	Operator findOne(UserDTO user) throws Exception;

	Operator findByEmpsn(String empSn) throws Exception;

	Operator findByUuid(String uuid) throws Exception;

	void save(Operator operator) throws Exception;

	void updateTheme(Long nodeId, String theme) throws Exception;

	void updateLastTask(Long nodeId, Long taskNodeId) throws Exception;
}
