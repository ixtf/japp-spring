package com.hengyi.japp.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;

import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public interface CrmService<CRM extends Crm> extends CommonUrlService<Long> {
	CRM newCrm();

	CRM findOne(Long nodeId);

	void save(CRM crm, Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			Iterable<CrmType> crmTypes, Iterable<Certificate> certificates,
			Communicatee communicatee, Iterable<Communicatee> communicatees,
			Iterable<Associate> associates) throws Exception;

	void delete(CRM crm) throws Exception;

	List<CRM> findAll(PageRequest pageRequest);

	long count();

	List<CRM> findAllByQuery(String nameSearch) throws Exception;

	List<Indicator> findAllIndicator();

	Map<Indicator, List<IndicatorValueScore>> getIndicatorMap(CRM crm,
			Iterable<Indicator> indicators);
}
