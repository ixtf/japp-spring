package com.hengyi.japp.common.service;

import com.hengyi.japp.common.data.PrincipalType;

public interface CacheService extends CommonCacheService {
	void setSessionData() throws Exception;

	void setSessionData(PrincipalType principalType, String principal)
			throws Exception;
}
