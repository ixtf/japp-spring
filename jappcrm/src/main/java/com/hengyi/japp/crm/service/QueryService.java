package com.hengyi.japp.crm.service;

import java.util.List;

import com.hengyi.japp.crm.data.CrmType;
import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.dto.CommunicateeDTO;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.dto.CrmDTO;
import com.hengyi.japp.crm.dto.CustomerDTO;
import com.hengyi.japp.crm.dto.OperatorDTO;
import com.hengyi.japp.crm.dto.StorageDTO;

public interface QueryService {
	OperatorDTO findOneOperator(String uuid);

	CorporationTypeDTO findOneCorporationType(Long nodeId);

	List<CorporationTypeDTO> findAllCorporationType();

	CertificateDTO findOneCertificate(Long nodeId);

	List<CertificateDTO> findAllCertificate();

	CommunicateeDTO findOneCommunicatee(Long nodeId);

	CrmDTO findOneCrm(CrmType crmType, Long nodeId);

	CustomerDTO findOneCustomer(Long nodeId);

	StorageDTO findOneStorage(Long nodeId);
}
