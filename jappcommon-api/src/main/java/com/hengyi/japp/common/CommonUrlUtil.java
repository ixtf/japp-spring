package com.hengyi.japp.common;

public class CommonUrlUtil {
	public String getHomePath() {
		return "/";
	}

	public String getHomeView() {
		return "/faces/home.jsf";
	}

	public String getLoginPath() {
		return "/login";
	}

	public String getLoginView() {
		return "/faces/login.jsf";
	}

	public String getLogoutPath() {
		return "/logout";
	}

	public String getThemePath() {
		return "/theme";
	}

	public String getThemeView() {
		return "/faces/theme.jsf";
	}

	public String getAdminHomePath() {
		return "/admin";
	}

	public String getAdminHomeView() {
		return "/faces/admin/index.jsf";
	}

	public String getUnauthorizedPath() {
		return "/unauthorized";
	}

	public String getBugNewPath() {
		return "/bug";
	}

	public String getBugUpdateView() {
		return "/faces/bug/update.jsf";
	}

	public String getBugsPath() {
		return "/bugs";
	}

	public String getBugsView() {
		return "/faces/bug/list.jsf";
	}
}
