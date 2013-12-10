package com.hengyi.japp.crm.application;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.event.CorporationTypeUpdateEvent;

@Component
@Transactional
public class CorporationTypeUpdateEventListener implements
		ApplicationListener<CorporationTypeUpdateEvent> {
	@Inject
	private Mapper dozer;
	@Inject
	private OperatorRepository operatorRepository;
	@Inject
	private CorporationTypeRepository corporationTypeRepository;

	@Override
	public void onApplicationEvent(CorporationTypeUpdateEvent event) {
		CorporationType entity = null;
		CorporationTypeDTO dto = event.getCorporationType();
		if (dto.getNodeId() == null)
			entity = new CorporationType();
		else
			entity = corporationTypeRepository.findOne(dto.getNodeId());
		dozer.map(dto, entity);
		entity.setOperator(operatorRepository.findOne(event.getOperatorNodeId()));
		corporationTypeRepository.save(entity);
		dto.setNodeId(entity.getNodeId());
	}

}
