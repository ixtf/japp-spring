package com.hengyi.japp.foreign.service.impl;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.foreign.domain.Operator;
import com.hengyi.japp.foreign.domain.repository.OperatorRepository;
import com.hengyi.japp.foreign.service.OperatorService;

@Named
@Transactional
public class OperatorServiceImpl implements OperatorService {
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;
	@Inject
	private OperatorRepository operatorRepository;

	@Override
	public Operator findOne(PrincipalType principalType, String principal)
			throws Exception {
		UserDTO user = jappCommonSoapClient.findOneUser(principalType,
				principal);
		Operator operator = operatorRepository.findOne(user.getUuid());
		if (operator == null) {
			operator = new Operator(user.getUuid(), user.getName());
			operatorRepository.save(operator);
		}
		return operator;
	}
}
