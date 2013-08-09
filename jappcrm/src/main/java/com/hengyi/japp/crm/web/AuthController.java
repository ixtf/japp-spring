package com.hengyi.japp.crm.web;

import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.common.data.PrincipalType;

@Named
@Scope("request")
// @Join(path = "/login", to = "/faces/login.jsf")
public class AuthController extends AbstractController {
	private static final long serialVersionUID = 3708518912737819900L;
	@Resource(name = "deployProperties")
	private Properties deployProperties;
	@NotBlank
	private String username;
	@NotBlank
	private String password;

	public void login() {
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);
			subject.login(token);
			addInfoMessage("登入成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void logout() {
		String url = "login";
		try {
			if (PrincipalType.SSO.equals(cacheService.getPrincipalType())) {
				url = deployProperties.getProperty("casLogoutUrl");
			}
		} catch (Exception e) {
			// TODO
		}
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		redirect(url);
	}

	public boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	public boolean isAdmin() {
		return SecurityUtils.getSubject().hasRole("admin");
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
