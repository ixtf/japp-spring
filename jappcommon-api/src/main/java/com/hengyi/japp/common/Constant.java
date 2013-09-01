package com.hengyi.japp.common;

import org.codehaus.jackson.map.ObjectMapper;

import com.hengyi.japp.common.data.Theme;

public class Constant {
	public static final String NAME_SPACE = "com.hengyi.japp.common";
	public static final String ADMIN_UUID = "ffc63a20-6a20-4ada-8ff7-627da3fac55d";
	public static final String ADMIN_PRINCIPAL = "admin";
	public static final String ADMIN_NAME = "系统管理员";
	public static final String SESSION_PRINCIPAL = "__SESSION_PRINCIPAL__";
	public static final String SESSION_PRINCIPALTYPE = "__SESSION_PRINCIPALTYPE__";
	public static final String SESSION_USER = "__SESSION_USER__";
	public static final String SESSION_OPERATOR = "__SESSION_OPERATOR__";
	public static final String DATEFORMAT_P = "yyyy-MM-dd";
	public static final String LOGIN_URL = "/login";
	public static final String LOGIN_VIEW = "login";
	public static final String LOGOUT_URL = "/logout";
	public static final String LOGOUT_SSO_URL = "/logout";
	public static final String UNAUTHORIZED_URL = "/unauthorized";
	public static final String UNAUTHORIZED_VIEW = "error/unauthorized";
	public static final int PAGE_SIZE = 10;

	public static final Theme DEFAULT_THEME = new Theme("bootstrap",
			"bootstrap");
	public static final ObjectMapper JSON = new ObjectMapper();

	public static class ErrorCode {
		public static final String SYSTEM = "error.system";
		public static final String EMP_SN_NOT_EXIST = "error.empSn.not.exist";
		public static final String USER_UNIQUE_NOT_EXIST = "error.user.unique.not.exist";
		public static final String USER_NOT_EXIST = "error.user.not.exist";
		public static final String BINDUSER_NOT_EXIST = "error.bindUser.not.exist";
		public static final String CORPORATION_NOT_EXIST = "error.corporation.not.exist";
		public static final String PASSWORD_INCORRECT = "error.password.incorrect";
		public static final String USER_LOCKED = "error.user.locked";
	}
}
