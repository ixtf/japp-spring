package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.crm.domain.Operator;

public interface CacheService extends CommonCacheService {
	Operator getCurrentOperator() throws Exception;
}
