package com.hengyi.japp.crm.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.service.CrmService;

@Named
@Transactional
public class CrmServiceImpl implements CrmService {
	@Inject
	protected Neo4jOperations template;
	@Inject
	private CrmRepository crmRepository;

	@Override
	public Crm findOne(Long nodeId) {
		return crmRepository.findOne(nodeId);
	}

	@Override
	public void save(Crm crm, Iterable<IndicatorValue> indicatorValues,
			CrmType crmType, Communicatee communicatee,
			Iterable<Communicatee> communicatees, Iterable<Associate> associates) {
		crm.setCrmType(crmType);
		crm.setCommunicatee(communicatee);
		crm.setCommunicatees(communicatees);
		crm.setAssociates(associates);
		crm.setIndicatorValues(indicatorValues);
		crmRepository.save(crm);
		for (Associate associate : associates)
			template.save(associate);
	}

	@Override
	public void delete(Crm crm) throws Exception {
		crmRepository.delete(crm);
	}
}
