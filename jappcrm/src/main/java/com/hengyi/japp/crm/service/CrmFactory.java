package com.hengyi.japp.crm.service;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.hengyi.japp.crm.data.CrmType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.dto.CrmDTO;

public interface CrmFactory {
	Crm newCrm(CrmType crmType);

	CrmDTO newCrmDTO(CrmType crmType);

	GraphRepository<? extends Crm> graphRepository(CrmType crmType);
}
