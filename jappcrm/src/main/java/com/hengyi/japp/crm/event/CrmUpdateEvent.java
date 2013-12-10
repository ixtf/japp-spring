package com.hengyi.japp.crm.event;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.application.AbstractApplicationEvent;
import com.hengyi.japp.crm.dto.CrmDTO;

@NodeEntity
public class CrmUpdateEvent extends AbstractApplicationEvent {
	public CrmUpdateEvent(Object source, Long operatorNodeId) {
		super(source, operatorNodeId);
	}

	public CrmDTO getCrm() {
		return (CrmDTO) getSource();
	}
}
