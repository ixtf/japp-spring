package com.hengyi.japp.common.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.hengyi.japp.common.Constant;
import com.hengyi.japp.common.domain.node.bind.user.InnerUser;
import com.hengyi.japp.common.service.CacheService;
import com.hengyi.japp.common.service.UserService;

public class LocalRealm extends JdbcRealm {
	@Resource
	private UserService userService;
	@Resource
	private CacheService cacheService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		InnerUser user = userService.findOneInnerUser(username);
		if (user == null)
			return null;
		cacheService.setSession(Constant.SESSION_USER, user.getUser());
		cacheService.setSession(Constant.SESSION_PRINCIPALTYPE,
				user.getPrincipalType());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,
				user.getPassword().toCharArray(), user.getPrincipalType()
						.name());
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}
}
