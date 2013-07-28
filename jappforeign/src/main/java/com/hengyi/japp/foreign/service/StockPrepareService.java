package com.hengyi.japp.foreign.service;

import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.domain.Operator;

public interface StockPrepareService {
	void bindVbap(String number, VbapPK pk, Operator operator);

	void finish(String number, Operator findOne) throws Exception;

	void unfinish(String number, Operator findOne) throws Exception;
}
