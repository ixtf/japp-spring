package com.hengyi.japp.foreign.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.service.AbstractCommonCacheService;
import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.Operator;
import com.hengyi.japp.foreign.domain.repository.OperatorRepository;
import com.hengyi.japp.foreign.service.CacheService;

@Named("cacheService")
@Singleton
public class CacheServiceImpl extends AbstractCommonCacheService implements
		CacheService {
	@Resource
	private OperatorRepository operatorRepository;

	@Override
	public Operator getCurrentOperator() throws Exception {
		Operator operator = getSession(Constant.SESSION_OPERATOR);
		if (operator == null) {
			UserDTO user = getSession(com.hengyi.japp.common.CommonConstant.SESSION_USER);
			operator = operatorRepository.findOne(user.getUuid());
			if (operator == null) {
				operator = new Operator(user.getUuid(), user.getName());
				operatorRepository.save(operator);
			}
			setSession(Constant.SESSION_OPERATOR, operator);
		}
		return operator;
	}
}
