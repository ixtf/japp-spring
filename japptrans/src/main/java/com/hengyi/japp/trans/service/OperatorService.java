package com.hengyi.japp.trans.service;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.trans.domain.Operator;

public interface OperatorService {
	Operator findOne(String uuid);

	Operator findOne(UserDTO user);

	void save(Operator operator) throws Exception;

	void updateTheme(String uuid, String theme) throws Exception;
}
