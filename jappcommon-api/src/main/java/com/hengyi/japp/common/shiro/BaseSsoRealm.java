package com.hengyi.japp.common.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hengyi.japp.common.data.PrincipalType;

public class BaseSsoRealm extends CasRealm {
	@Resource(name = "jappCommonSoapClient")
	protected com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		AuthenticationInfo info = super.doGetAuthenticationInfo(token);
		try {
			if (info != null)
				ShiroUtil
						.setSession(PrincipalType.SSO, info.getPrincipals()
								.getPrimaryPrincipal().toString(),
								jappCommonSoapClient);
			return info;
		} catch (Exception e) {
			throw new AuthenticationException(e);
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}
}