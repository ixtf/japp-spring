package com.hengyi.japp.common.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.LoginUserDTO;

public class BaseRealm extends JdbcRealm {
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		try {
			LoginUserDTO loginUser = jappCommonSoapClient.login(username,
					String.valueOf(upToken.getPassword()));
			PrincipalType principalType = loginUser.getPrincipalType();
			String principal = loginUser.getPrincipal();
			ShiroUtil
					.setSession(principalType, principal, jappCommonSoapClient);
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
					username, null, principalType.name());
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

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		super.setCredentialsMatcher(new AllowAllCredentialsMatcher());
	}
}