package com.hengyi.japp.common.service;

import java.util.List;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.data.Theme;
import com.hengyi.japp.common.dto.UserDTO;

public interface CommonCacheService {
	PrincipalType getPrincipalType() throws Exception;

	Object getPrincipal() throws Exception;

	UserDTO getUser() throws Exception;

	List<Theme> getThemes();

	String getTheme();

	<T> T getSession(Object key);

	void setSession(Object key, Object value);

	boolean isAuthenticated();

	boolean isAdmin();

	String getHomePath();

	String getHomeView();

	String getLoginPath();

	String getLoginView();

	String getLogoutPath();

	String getThemePath();

	String getThemeView();

	String getAdminHomePath();

	String getAdminHomeView();

	String getUnauthorizedPath();

	String getBugNewPath();

	String getBugUpdateView();

	String getBugsPath();

	String getBugsView();
}
