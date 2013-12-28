package com.hengyi.japp.crm.application;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.repository.CrmFieldRepository;
import com.hengyi.japp.crm.dto.CrmFieldDTO;
import com.hengyi.japp.crm.event.CrmFieldUpdateEvent;

@Named
@Transactional
public class CrmFieldUpdateEventListener implements
		ApplicationListener<CrmFieldUpdateEvent> {
	@Inject
	private Mapper dozer;
	@Inject
	private CrmFieldRepository crmFieldRepository;

	@Override
	public void onApplicationEvent(CrmFieldUpdateEvent event) {
		CrmFieldDTO dto = event.getCrmField();
		Long nodeId = dto.getNodeId();
		CrmField crmField = nodeId == null ? new CrmField()
				: crmFieldRepository.findOne(nodeId);
		dozer.map(dto, crmField);
		crmFieldRepository.save(crmField);
		dto.setNodeId(crmField.getNodeId());
	}
}
