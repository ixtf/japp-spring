package com.hengyi.japp.foreign.service;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.foreign.domain.Operator;

public interface CacheService extends CommonCacheService {
	Operator getCurrentOperator() throws Exception;
}
