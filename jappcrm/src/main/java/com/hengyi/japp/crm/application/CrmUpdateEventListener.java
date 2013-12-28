package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.data.CrmType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.IndicatorValueRepository;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.dto.CrmDTO;
import com.hengyi.japp.crm.event.CrmUpdateEvent;

@Named
@Transactional
public class CrmUpdateEventListener implements
		ApplicationListener<CrmUpdateEvent> {
	@Inject
	private Mapper dozer;
	@Inject
	private OperatorRepository operatorRepository;
	@Inject
	private CrmRepository crmRepository;
	@Inject
	private IndicatorValueRepository indicatorValueRepository;

	@Override
	public void onApplicationEvent(CrmUpdateEvent event) {
		CrmDTO dto = event.getCrm();
		CrmType crmType = dto.getCrmType();
		Long nodeId = dto.getNodeId();
		Crm crm = nodeId == null ? crmType.newEntity() : crmRepository
				.findOne(nodeId);
		dozer.map(dto, crm);
		crm.setIndicatorValues(indicatorValueRepository.findAll(event
				.getIndicatorValueIds()));
		crm.setOperator(operatorRepository.findOne(event.getOperatorNodeId()));
		crmRepository.save(crm);
	}
}
