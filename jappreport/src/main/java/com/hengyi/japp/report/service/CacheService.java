package com.hengyi.japp.report.service;

import java.io.Serializable;

import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.report.domain.Operator;

public interface CacheService extends CommonCacheService, Serializable {
	Operator getCurrentOperator() throws Exception;
}
