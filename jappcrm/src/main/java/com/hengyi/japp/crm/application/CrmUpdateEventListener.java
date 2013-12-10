package com.hengyi.japp.crm.application;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.dto.CrmDTO;
import com.hengyi.japp.crm.event.CrmUpdateEvent;
import com.hengyi.japp.crm.service.CrmFactory;

@Component
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
	private CrmFactory crmFactory;

	@Override
	public void onApplicationEvent(CrmUpdateEvent event) {
		Crm entity = null;
		CrmDTO dto = event.getCrm();
		if (dto.getNodeId() == null)
			entity = crmFactory.newCrm(event.getCrm().getCrmType());
		else
			entity = crmRepository.findOne(dto.getNodeId());
		dozer.map(dto, entity);
		entity.setOperator(operatorRepository.findOne(event.getOperatorNodeId()));
		crmRepository.save(entity);
		dto.setNodeId(entity.getNodeId());
	}

}
