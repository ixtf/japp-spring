package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;

public interface CrmFieldService extends CommonCrudNeo4jService<CrmField> {
	void save(CrmField report, Iterable<Indicator> indicators);
}
