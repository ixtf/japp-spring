package com.hengyi.japp.foreign.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.Operator;
import com.hengyi.japp.foreign.domain.repository.OperatorRepository;
import com.hengyi.japp.foreign.service.CacheService;

@SuppressWarnings("unchecked")
@Singleton
@Named
public class CacheServiceImpl implements CacheService {
	@Inject
	private OperatorRepository operatorRepository;

	@Override
	public Operator getCurrentOperator() throws Exception {
		Operator operator = getSession(Constant.SESSION_OPERATOR,
				Operator.class);
		if (operator == null) {
			UserDTO user = getSession(
					com.hengyi.japp.common.Constant.SESSION_USER, UserDTO.class);
			operator = operatorRepository.findOne(user.getUuid());
			if (operator == null) {
				operator = new Operator(user.getUuid(), user.getName());
				operatorRepository.save(operator);
			}
			setSession(Constant.SESSION_OPERATOR, operator);
		}
		return operator;
	}

	@Override
	public void setSession(Object key, Object value) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.setAttribute(key, value);
	}

	@Override
	public <T> T getSession(Object key, Class<T> clazz) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return (T) session.getAttribute(key);
	}

}
