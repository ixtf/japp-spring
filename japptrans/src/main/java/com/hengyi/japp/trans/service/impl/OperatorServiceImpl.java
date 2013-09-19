package com.hengyi.japp.trans.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.trans.domain.Operator;
import com.hengyi.japp.trans.domain.repository.OperatorRepository;
import com.hengyi.japp.trans.service.OperatorService;

@Named
@Transactional
public class OperatorServiceImpl implements OperatorService {
	@Inject
	private OperatorRepository operatorRepository;

	@Override
	public Operator findOne(String uuid) {
		return uuid == null ? null : operatorRepository.findOne(uuid);
	}

	@Override
	public Operator findOne(UserDTO user) {
		Operator operator = findOne(user.getUuid());
		if (operator == null) {
			operator = new Operator(user.getUuid(), user.getName());
			save(operator);
		}
		return operator;
	}

	@Override
	public void save(Operator operator) {
		operatorRepository.save(operator);
	}

	@Override
	public void updateTheme(String uuid, String theme) throws Exception {
		Operator operator = findOne(uuid);
		operator.setTheme(theme);
		save(operator);
	}
}
