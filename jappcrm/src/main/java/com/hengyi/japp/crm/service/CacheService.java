package com.hengyi.japp.crm.service;

import static com.hengyi.japp.common.CommonUtil.urlService;

import com.hengyi.japp.common.service.AbstractUrlService;
import com.hengyi.japp.common.service.CommonCacheService;
import com.hengyi.japp.crm.domain.Operator;

public interface CacheService extends CommonCacheService {
	static final AbstractUrlService customerUrlService = urlService("/customer");
	static final AbstractUrlService storageUrlService = urlService("/storage");

	Operator getCurrentOperator() throws Exception;
}
