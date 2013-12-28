package com.hengyi.japp.crm.service;

import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.dto.CommunicateeDTO;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.dto.CustomerDTO;
import com.hengyi.japp.crm.dto.StorageDTO;

public interface CommandService {
	CorporationTypeDTO save(CorporationTypeDTO dto);

	CertificateDTO save(CertificateDTO dto);

	CommunicateeDTO save(CommunicateeDTO dto);

	CustomerDTO save(CustomerDTO dto);

	StorageDTO save(StorageDTO dto);
}
