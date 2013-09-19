package com.hengyi.japp.trans.service;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.trans.domain.Operator;

public interface CacheService extends CommonCacheService {
	Operator getCurrentOperator() throws Exception;
}
