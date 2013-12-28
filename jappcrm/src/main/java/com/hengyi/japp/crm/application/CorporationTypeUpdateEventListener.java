package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.event.CorporationTypeUpdateEvent;

@Named
@Transactional
public class CorporationTypeUpdateEventListener implements
		ApplicationListener<CorporationTypeUpdateEvent> {
	@Inject
	private Mapper dozer;
	@Inject
	private OperatorRepository operatorRepository;
	@Inject
	private EventBus eventBus;
	@Inject
	private CorporationTypeRepository corporationTypeRepository;

	@Override
	public void onApplicationEvent(CorporationTypeUpdateEvent event) {
		CorporationTypeDTO dto = event.getCorporationType();
		Long nodeId = dto.getNodeId();
		CorporationType corporationType = nodeId == null ? new CorporationType()
				: corporationTypeRepository.findOne(nodeId);
		dozer.map(dto, corporationType);
		corporationType.setOperator(operatorRepository.findOne(event
				.getOperatorNodeId()));
		corporationTypeRepository.save(corporationType);
		dto.setNodeId(corporationType.getNodeId());
		eventBus.post(event);
	}
}
