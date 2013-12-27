package com.hengyi.japp.crm.service;

import com.hengyi.japp.crm.dto.CustomerDTO;
import com.hengyi.japp.crm.dto.StorageDTO;

public interface QueryService {
	CustomerDTO findOneCustomer(Long nodeId);

	StorageDTO findOneStorage(Long nodeId);
}
