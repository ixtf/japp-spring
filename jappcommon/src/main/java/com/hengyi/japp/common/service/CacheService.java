package com.hengyi.japp.common.service;

import com.hengyi.japp.common.data.PrincipalType;

public interface CacheService {
	void setSessionData() throws Exception;

	void setSessionData(PrincipalType principalType, String principal)
			throws Exception;

	<T> T getSessionData(String key, Class<T> T);

	void setSessionData(String key, Object value);
}
