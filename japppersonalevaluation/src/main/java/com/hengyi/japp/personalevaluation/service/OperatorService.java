package com.hengyi.japp.personalevaluation.service;

import com.hengyi.japp.personalevaluation.domain.node.Operator;

public interface OperatorService {
	Operator findOne(Long nodeId) throws Exception;

	Operator findByEmpsn(String empSn) throws Exception;

	Operator findByUuid(String uuid) throws Exception;
}
