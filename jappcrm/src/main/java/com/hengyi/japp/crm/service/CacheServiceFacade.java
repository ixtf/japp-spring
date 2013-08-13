package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CacheService;
import com.hengyi.japp.crm.domain.node.Operator;

public interface CacheServiceFacade extends CacheService {
	Operator getCurrentOperator() throws Exception;
}
