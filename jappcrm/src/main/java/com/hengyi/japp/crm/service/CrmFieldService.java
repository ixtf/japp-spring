package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CrmField;

public interface CrmFieldService extends CommonCrudNeo4jService<CrmField> {
	void save(Iterable<CrmField> crmFileds);
}
