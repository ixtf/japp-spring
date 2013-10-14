package com.hengyi.japp.crm.service;

import java.io.Serializable;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.crm.domain.Operator;

public interface CacheService extends CommonCacheService, Serializable {
	Operator getCurrentOperator() throws Exception;
}
