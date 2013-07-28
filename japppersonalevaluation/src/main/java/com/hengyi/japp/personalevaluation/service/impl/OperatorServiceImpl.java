package com.hengyi.japp.personalevaluation.service.impl;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.repository.OperatorRepository;
import com.hengyi.japp.personalevaluation.service.OperatorService;

@Named
@Transactional
// @Veto
public class OperatorServiceImpl implements OperatorService {
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;
	@Inject
	private Neo4jOperations template;
	@Inject
	private OperatorRepository operatorRepository;

	@Override
	public Operator findOne(Long nodeId) throws Exception {
		return nodeId == null ? null : operatorRepository.findOne(nodeId);
	}

	@Override
	public Operator findByUuid(String uuid) {
		return uuid == null ? null : operatorRepository.findByPropertyValue(
				Operator.class.getSimpleName(), "uuid", uuid);
	}

	@Override
	public Operator findByEmpsn(String empSn) throws Exception {
		Operator operator = operatorRepository.findByPropertyValue(
				Operator.EMPSN_SEARCH, "empSn", empSn);
		return operator != null ? operator : getOperatorFromCommon(empSn);
	}

	private Operator getOperatorFromCommon(String empSn) throws Exception {
		UserDTO user = jappCommonSoapClient
				.findOneUser(PrincipalType.HR, empSn);
		if (user == null)
			return null;
		Operator operator = findByUuid(user.getUuid());
		if (operator != null) {
			if (operator.getEmpSn() == null) {
				operator.setEmpSn(empSn);
				return operatorRepository.save(operator);
			}
			return operator;
		}
		operator = new Operator();
		operator.setUuid(user.getUuid());
		operator.setName(user.getName());
		operator.setEmpSn(empSn);
		return operatorRepository.save(operator);
	}
}
