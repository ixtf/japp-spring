package com.hengyi.japp.foreign.service;

import com.hengyi.japp.foreign.domain.Operator;

public interface CacheService {
	<T> T getSession(Object key, Class<T> value) throws Exception;

	void setSession(Object key, Object value) throws Exception;

	Operator getCurrentOperator() throws Exception;
}
