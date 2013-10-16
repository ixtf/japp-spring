package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.common.service.CommonUrlService;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;

public interface CrmFieldService<T extends CrmField> extends
		CommonUrlService<Long> {
	T newCrmField();

	T findOne(Long nodeId);

	void save(T report, Iterable<Indicator> indicators);

	void delete(T report);

	List<T> findAll();
}
