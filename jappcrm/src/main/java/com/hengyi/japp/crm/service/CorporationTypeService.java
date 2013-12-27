package com.hengyi.japp.crm.service;

import com.hengyi.japp.common.service.CommonCrudNeo4jService;
import com.hengyi.japp.crm.domain.CorporationType;

public interface CorporationTypeService extends
		CommonCrudNeo4jService<CorporationType> {

	void save(Iterable<CorporationType> corporationTypes);

	void save(CorporationType crmType);

	void delete(CorporationType crmType);
}
