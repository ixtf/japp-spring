package com.hengyi.japp.common.service.impl;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.hengyi.japp.common.Constant;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.domain.repository.UserRepository;
import com.hengyi.japp.common.service.CacheService;
import com.hengyi.japp.common.service.UserService;

@Named
@Singleton
public class CacheServiceImpl extends CommonCacheServiceImpl implements
		CacheService {
	@Resource
	private UserRepository userRepository;
	@Inject
	private UserService userService;

	@Override
	public void setSessionData() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		if (subject.getPrincipal().equals(Constant.ADMIN_PRINCIPAL)) {
			Session session = subject.getSession();
			session.setAttribute(Constant.SESSION_USER, new User(
					Constant.ADMIN_NAME));
			session.setAttribute(Constant.SESSION_PRINCIPALTYPE,
					PrincipalType.INI);
			return;
		}
		for (String s : subject.getPrincipals().getRealmNames()) {
			setSessionData(PrincipalType.valueOf(s), subject.getPrincipal()
					.toString());
		}
	}

	@Override
	public void setSessionData(PrincipalType principalType, String principal)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		BindUser bindUser = userService.findOneBindUser(principalType,
				principal);
		session.setAttribute(Constant.SESSION_USER, bindUser.getUser());
		session.setAttribute(Constant.SESSION_PRINCIPALTYPE, principalType);
	}
}
