package com.hengyi.japp.common.shiro;

import static com.hengyi.japp.common.Constant.SESSION_PRINCIPAL;
import static com.hengyi.japp.common.Constant.SESSION_PRINCIPALTYPE;
import static com.hengyi.japp.common.Constant.SESSION_USER;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.LoginUserDTO;
import com.hengyi.japp.common.dto.UserDTO;

public class BaseRealm_back extends CasRealm {
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;

	@Override
	public boolean supports(AuthenticationToken token) {
		return super.supports(token) || isUsernamePasswordToken(token);
	}

	private boolean isUsernamePasswordToken(AuthenticationToken token) {
		return token != null
				&& UsernamePasswordToken.class.isAssignableFrom(token
						.getClass());
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		try {
			if (isUsernamePasswordToken(token))
				return doGetAuthenticationInfoBase(token);
			else
				return doGetAuthenticationInfoSSO(token);
		} catch (Exception e) {
			throw new AuthenticationException(e);
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}

	private AuthenticationInfo doGetAuthenticationInfoBase(
			AuthenticationToken token) throws Exception {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		LoginUserDTO loginUser = jappCommonSoapClient.login(username,
				String.valueOf(upToken.getPassword()));
		PrincipalType principalType = loginUser.getPrincipalType();
		String principal = loginUser.getPrincipal();
		setSession(principalType, principal);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,
				null, principalType.name());
		return info;
	}

	private AuthenticationInfo doGetAuthenticationInfoSSO(
			AuthenticationToken token) throws Exception {
		AuthenticationInfo info = super.doGetAuthenticationInfo(token);
		if (info != null)
			setSession(PrincipalType.SSO, info.getPrincipals()
					.getPrimaryPrincipal().toString());
		return info;
	}

	protected void setSession(PrincipalType principalType, String principal)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		UserDTO user = jappCommonSoapClient.findOneUser(principalType,
				principal);
		session.setAttribute(SESSION_PRINCIPALTYPE, principalType);
		session.setAttribute(SESSION_PRINCIPAL, principal);
		session.setAttribute(SESSION_USER, user);
	}
}