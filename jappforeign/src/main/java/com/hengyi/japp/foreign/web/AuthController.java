package com.hengyi.japp.foreign.web;

import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class AuthController extends AbstractController {
	private static final long serialVersionUID = 3708518912737819900L;

	@Resource(name = "deployProperties")
	private Properties deployProperties;

	private String username;
	private String password;

	public void login() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		subject.login(token);
	}

	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
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

	// @RequestMapping(value = Constant.LOGIN_URL, method = RequestMethod.GET)
	// public ModelAndView loginForm() {
	// return new ModelAndView(Constant.LOGIN_VIEW);
	// }
	//
	// @RequestMapping(value = Constant.UNAUTHORIZED_URL, method =
	// RequestMethod.GET)
	// public ModelAndView unauthorized() {
	// return new ModelAndView(Constant.UNAUTHORIZED_VIEW);
	// }
	//
	// @RequestMapping(value = Constant.LOGIN_URL, method = RequestMethod.POST)
	// public void login(
	// @RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String
	// username,
	// @RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String
	// password,
	// @RequestParam(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM) boolean
	// rememberMe,
	// Model model) throws AppException {
	// Subject currentUser = SecurityUtils.getSubject();
	// UsernamePasswordToken token = new UsernamePasswordToken(username,
	// password);
	// token.setRememberMe(rememberMe);
	// if (!currentUser.isAuthenticated()) {
	// try {
	// currentUser.login(token);
	// } catch (UnknownAccountException e) {
	// FacesContext.getCurrentInstance().addMessage(
	// null,
	// new FacesMessage(FacesMessage.SEVERITY_ERROR,
	// getRootErrorMessage(e), getMessage(
	// Constant.ErrorCode.USER_NOT_EXSIT,
	// token.getUsername())));
	// // addErrorMessage(model, Constant.ErrorCode.USER_NOT_EXSIT,
	// // token.getUsername());
	// } catch (IncorrectCredentialsException e) {
	// FacesContext
	// .getCurrentInstance()
	// .addMessage(
	// null,
	// new FacesMessage(
	// FacesMessage.SEVERITY_ERROR,
	// getRootErrorMessage(e),
	// getMessage(Constant.ErrorCode.PASSWORD_INCORRECT)));
	// // addErrorMessage(model,
	// // Constant.ErrorCode.PASSWORD_INCORRECT);
	// } catch (LockedAccountException e) {
	// FacesContext.getCurrentInstance().addMessage(
	// null,
	// new FacesMessage(FacesMessage.SEVERITY_ERROR,
	// getRootErrorMessage(e),
	// getMessage(Constant.ErrorCode.USER_LOCKED)));
	// // addErrorMessage(model, Constant.ErrorCode.USER_LOCKED,
	// // token.getUsername());
	// } catch (AuthenticationException e) {
	// throw new SystemException(e);
	// }
	// }
	// }
	//
	// // 退出
	// @RequestMapping(value = Constant.LOGOUT_URL, method = RequestMethod.GET)
	// public void logout(Model model) {
	// Subject currentUser = SecurityUtils.getSubject();
	// currentUser.logout();
	// }
}
