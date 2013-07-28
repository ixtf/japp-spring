package com.hengyi.japp.foreign.service;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.foreign.domain.Operator;

public interface OperatorService {
	Operator findOne(PrincipalType principalType, String principal)
			throws Exception;
}
