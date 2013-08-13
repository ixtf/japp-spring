package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.crm.domain.node.Operator;

public interface OperatorService {
	Operator findOne(Long nodeId) throws Exception;

	Operator findOne(String uuid) throws Exception;

	Operator findOne(UserDTO user) throws Exception;

	void save(Operator operator) throws Exception;

	void updateTheme(String uuid, String theme) throws Exception;
}
