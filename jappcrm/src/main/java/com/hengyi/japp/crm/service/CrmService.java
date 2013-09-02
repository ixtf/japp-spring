package com.hengyi.japp.crm.service;

import java.util.List;
import java.util.Map;

import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public interface CrmService {
	Crm findOne(Long nodeId);

	void save(Crm crm, Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			CrmType crmType, Communicatee communicatee,
			Iterable<Communicatee> communicatees, Iterable<Associate> associates);

	void delete(Crm crm) throws Exception;
}
