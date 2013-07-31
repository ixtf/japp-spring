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
	public Operator findOne(UserDTO user) throws Exception {
		Operator operator = findByUuid(user.getUuid());
		if (operator == null) {
			operator = new Operator(user.getUuid(), user.getName());
			save(operator);
		}
		return operator;
	}

	@Override
	public void save(Operator operator) throws Exception {
		operatorRepository.save(operator);
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
		Operator operator = findOne(user);
		if (operator != null && operator.getEmpSn() == null) {
			operator.setEmpSn(empSn);
			save(operator);
		}
		return operator;
	}

	@Override
	public void updateTheme(Long nodeId, String theme) throws Exception {
		Operator operator = findOne(nodeId);
		operator.setTheme(theme);
		save(operator);
	}

	@Override
	public void updateLastTask(Long nodeId, Long taskNodeId) throws Exception {
		Operator operator = findOne(nodeId);
		operator.setLastTaskNodeId(taskNodeId);
		save(operator);
	}
}
