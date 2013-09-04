package com.hengyi.japp.common.service;

import java.util.List;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.data.Theme;
import com.hengyi.japp.common.dto.UserDTO;

public interface CacheService {
	PrincipalType getPrincipalType() throws Exception;

	Object getPrincipal() throws Exception;

	List<Theme> getThemes();

	String getTheme();

	<T> T getSession(Object key, Class<T> value) throws Exception;

	void setSession(Object key, Object value) throws Exception;

	UserDTO getUser() throws Exception;

	// AsyncEventBus getAsyncEventBus();
}
