package com.hengyi.japp.crm.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hengyi.japp.crm.data.CrmType;
import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.dto.CommunicateeDTO;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.dto.CrmDTO;

public interface QueryService {
	CertificateDTO findOneCertificate(Long nodeId);

	List<CertificateDTO> findAllCertificate();

	CorporationTypeDTO findOneCorporationType(Long nodeId);

	List<CorporationTypeDTO> findAllCorporationType();

	CommunicateeDTO findOneCommunicatee(Long nodeId);

	List<CommunicateeDTO> findAllCommunicatee(Pageable pageable);

	CrmDTO findOneCrm(Long nodeId);

	List<CrmDTO> findAllCrm(CrmType crmType, Pageable pageable);

	CommunicateeDTO findChiefCommunicatee(Long crmNodeId);

	List<CommunicateeDTO> findAllCommunicatees(Long crmNodeId);
}
