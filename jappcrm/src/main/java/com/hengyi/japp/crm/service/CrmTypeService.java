package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CrmType;

public interface CrmTypeService extends CommonCrudNeo4jService<CrmType> {

	void save(Iterable<CrmType> crmTypes);

	void save(CrmType crmType);

	void delete(CrmType crmType);
}
