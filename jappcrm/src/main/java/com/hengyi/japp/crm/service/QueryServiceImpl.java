package com.hengyi.japp.crm.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.MyUtil;
import com.hengyi.japp.crm.data.CrmType;
import com.hengyi.japp.crm.domain.Operator;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.domain.repository.CommunicateeRepository;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.CustomerRepository;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.domain.repository.StorageRepository;
import com.hengyi.japp.crm.dto.CertificateDTO;
import com.hengyi.japp.crm.dto.CommunicateeDTO;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.dto.CrmDTO;
import com.hengyi.japp.crm.dto.CustomerDTO;
import com.hengyi.japp.crm.dto.OperatorDTO;
import com.hengyi.japp.crm.dto.StorageDTO;

@Named
@Transactional
public class QueryServiceImpl implements QueryService {
	@Inject
	private Mapper dozer;
	@Inject
	private OperatorRepository operatorRepository;
	@Inject
	private CrmRepository crmRepository;
	@Inject
	private CustomerRepository customerRepository;
	@Inject
	private StorageRepository storageRepository;
	@Inject
	private CertificateRepository certificateRepository;
	@Inject
	private CommunicateeRepository communicateeRepository;
	@Inject
	private CorporationTypeRepository corporationTypeRepository;

	@Override
	public CorporationTypeDTO findOneCorporationType(Long nodeId) {
		return dozer.map(corporationTypeRepository.findOne(nodeId),
				CorporationTypeDTO.class);
	}

	@Override
	public List<CorporationTypeDTO> findAllCorporationType() {
		return MyUtil.convert(dozer, corporationTypeRepository.findAll(),
				CorporationTypeDTO.class);
	}

	@Override
	public CertificateDTO findOneCertificate(Long nodeId) {
		return dozer.map(certificateRepository.findOne(nodeId),
				CertificateDTO.class);
	}

	@Override
	public List<CertificateDTO> findAllCertificate() {
		return MyUtil.convert(dozer, certificateRepository.findAll(),
				CertificateDTO.class);
	}

	@Override
	public CommunicateeDTO findOneCommunicatee(Long nodeId) {
		return dozer.map(communicateeRepository.findOne(nodeId),
				CommunicateeDTO.class);
	}

	@Override
	public CustomerDTO findOneCustomer(Long nodeId) {
		return dozer.map(customerRepository.findOne(nodeId), CustomerDTO.class);
	}

	@Override
	public StorageDTO findOneStorage(Long nodeId) {
		return dozer.map(storageRepository.findOne(nodeId), StorageDTO.class);
	}

	@Override
	public OperatorDTO findOneOperator(String uuid) {
		return dozer.map(
				operatorRepository.findByPropertyValue(
						Operator.class.getSimpleName(), "uuid", uuid),
				OperatorDTO.class);
	}

	@Override
	public CrmDTO findOneCrm(CrmType crmType, Long nodeId) {
		CrmDTO result = crmType.newDTO();
		dozer.map(crmRepository.findOne(nodeId), result);
		return result;
	}
}
