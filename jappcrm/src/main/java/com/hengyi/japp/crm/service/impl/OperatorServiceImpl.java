package com.hengyi.japp.crm.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.crm.domain.node.Operator;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.service.OperatorService;

@Named
@Transactional
// @Veto
public class OperatorServiceImpl implements OperatorService {
	@Inject
	private OperatorRepository operatorRepository;

	@Override
	public Operator findOne(Long nodeId) {
		return nodeId == null ? null : operatorRepository.findOne(nodeId);
	}

	@Override
	public Operator findOne(String uuid) {
		return uuid == null ? null : operatorRepository.findByPropertyValue(
				Operator.class.getSimpleName(), "uuid", uuid);
	}

	@Override
	public Operator findOne(UserDTO user) throws Exception {
		Operator operator = findOne(user.getUuid());
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
	public void updateTheme(String uuid, String theme) throws Exception {
		Operator operator = findOne(uuid);
		operator.setTheme(theme);
		save(operator);
	}
}
