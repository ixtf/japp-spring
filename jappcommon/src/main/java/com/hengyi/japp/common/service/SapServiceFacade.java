package com.hengyi.japp.common.service;

import java.util.Collection;

import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.service.SapService;

public interface SapServiceFacade extends SapService {
	Collection<HrOrganizationDTO> findAllOrganization(String empSn);
}
