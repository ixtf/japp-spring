package com.hengyi.japp.common.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

public class JdbcRealm extends org.apache.shiro.realm.jdbc.JdbcRealm {
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}
}
