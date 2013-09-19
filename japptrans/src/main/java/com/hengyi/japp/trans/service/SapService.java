package com.hengyi.japp.trans.service;

import com.hengyi.japp.common.service.CommonSapService;
import com.hengyi.japp.trans.domain.PackType;
import com.hengyi.japp.trans.domain.TransType;

public interface SapService extends CommonSapService {
	Iterable<PackType> findAllPackType() throws Exception;

	Iterable<TransType> findAllTransType() throws Exception;
}
