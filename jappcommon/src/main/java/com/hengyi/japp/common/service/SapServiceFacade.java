package com.hengyi.japp.common.service;

import java.util.Collection;

import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.service.CommonSapService;

public interface SapServiceFacade extends CommonSapService {
	Collection<HrOrganizationDTO> findAllOrganization(String empSn);
}
