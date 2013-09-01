package com.hengyi.japp.crm.service;

import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.IndicatorValue;

public interface CrmService {
	Crm findOne(Long nodeId);

	void save(Crm crm, Iterable<IndicatorValue> indicatorValues,
			CrmType crmType, Communicatee communicatee,
			Iterable<Communicatee> communicatees, Iterable<Associate> associates);

	void delete(Crm crm) throws Exception;
}
