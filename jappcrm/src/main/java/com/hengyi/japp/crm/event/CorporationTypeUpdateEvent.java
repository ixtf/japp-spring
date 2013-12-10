package com.hengyi.japp.crm.event;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.application.AbstractApplicationEvent;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;

@NodeEntity
public class CorporationTypeUpdateEvent extends AbstractApplicationEvent {
	public CorporationTypeUpdateEvent(Object source, Long operatorNodeId) {
		super(source, operatorNodeId);
	}

	public CorporationTypeDTO getCorporationType() {
		return (CorporationTypeDTO) getSource();
	}

}
