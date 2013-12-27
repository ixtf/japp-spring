package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.Report;

public interface ReportService<T extends Report> extends
		CommonCrudNeo4jService<T> {
	T newReport();

	void save(T report, Iterable<Indicator> indicators,
			Iterable<CrmField> crmFields) throws Exception;
}
