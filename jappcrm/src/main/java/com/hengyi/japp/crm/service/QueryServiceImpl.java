package com.hengyi.japp.crm.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.data.CrmType;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.domain.repository.CommunicateeRepository;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.dto.CommunicateeDTO;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.dto.CrmDTO;

@Named
@Transactional
public class QueryServiceImpl implements QueryService {
	@Inject
	private Mapper dozer;
	@Inject
	private CertificateRepository certificateRepository;
	@Inject
	private CorporationTypeRepository corporationTypeRepository;
	@Inject
	private CommunicateeRepository communicateeRepository;
	@Inject
	private CrmRepository crmRepository;
	@Inject
	private CrmFactory crmFactory;

	@Override
	public CertificateDTO findOneCertificate(Long nodeId) {
		return dozer.map(certificateRepository.findOne(nodeId),
				CertificateDTO.class);
	}

	@Override
	public List<CertificateDTO> findAllCertificate() {
		List<CertificateDTO> result = Lists.newArrayList();
		for (Certificate certificate : certificateRepository.findAll())
			result.add(dozer.map(certificate, CertificateDTO.class));
		return result;
	}

	@Override
	public CorporationTypeDTO findOneCorporationType(Long nodeId) {
		return dozer.map(corporationTypeRepository.findOne(nodeId),
				CorporationTypeDTO.class);
	}

	@Override
	public List<CorporationTypeDTO> findAllCorporationType() {
		List<CorporationTypeDTO> result = Lists.newArrayList();
		for (CorporationType corporationType : corporationTypeRepository
				.findAll())
			result.add(dozer.map(corporationType, CorporationTypeDTO.class));
		return result;
	}

	@Override
	public CrmDTO findOneCrm(Long nodeId) {
		Crm crm = crmRepository.findOne(nodeId);
		CrmDTO result = crmFactory.newCrmDTO(crm.getCrmType());
		dozer.map(crm, result);
		return result;
	}

	@Override
	public CommunicateeDTO findChiefCommunicatee(Long crmNodeId) {
		Crm crm = crmRepository.findOne(crmNodeId);
		return dozer.map(crm.getCommunicatee(), CommunicateeDTO.class);
	}

	@Override
	public List<CommunicateeDTO> findAllCommunicatees(Long crmNodeId) {
		List<CommunicateeDTO> result = Lists.newArrayList();
		Crm crm = crmRepository.findOne(crmNodeId);
		for (Communicatee communicatee : crm.getCommunicatees())
			result.add(dozer.map(communicatee, CommunicateeDTO.class));
		return result;
	}

	@Override
	public CommunicateeDTO findOneCommunicatee(Long nodeId) {
		return dozer.map(communicateeRepository.findOne(nodeId),
				CommunicateeDTO.class);
	}

	@Override
	public List<CommunicateeDTO> findAllCommunicatee(Pageable pageable) {
		List<CommunicateeDTO> result = Lists.newArrayList();
		for (Communicatee communicatee : communicateeRepository
				.findAll(pageable))
			result.add(dozer.map(communicatee, CommunicateeDTO.class));
		return result;
	}

	@Override
	public List<CrmDTO> findAllCrm(CrmType crmType, Pageable pageable) {
		List<CrmDTO> result = Lists.newArrayList();
		for (Crm crm : crmFactory.graphRepository(crmType).findAll(pageable)) {
			CrmDTO dto = crmFactory.newCrmDTO(crm.getCrmType());
			dozer.map(crm, dto);
			result.add(dto);
		}
		return result;
	}
}
