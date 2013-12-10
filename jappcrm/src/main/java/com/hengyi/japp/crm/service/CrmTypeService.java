package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CorporationType;

public interface CrmTypeService extends CommonCrudNeo4jService<CorporationType> {

	void save(Iterable<CorporationType> crmTypes);

	void save(CorporationType crmType);

	void delete(CorporationType crmType);
}
