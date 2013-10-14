package com.hengyi.japp.crm.service;

import java.io.Serializable;
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

public interface CrmService<T extends Crm> extends CommonUrlService<Long>,
		Serializable {
	T newCrm();

	T findOne(Long nodeId);

	void save(T crm, Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			CrmType crmType, Iterable<Certificate> certificates,
			Communicatee communicatee, Iterable<Communicatee> communicatees,
			Iterable<Associate> associates) throws Exception;

	void delete(T crm) throws Exception;

	List<T> findAll(PageRequest pageRequest);

	long count();

	List<T> findAllByQuery(String nameSearch) throws Exception;

	List<Indicator> findAllIndicator();

	Map<Indicator, List<IndicatorValueScore>> getIndicatorMap(Crm crm,
			Iterable<Indicator> indicators);
}
