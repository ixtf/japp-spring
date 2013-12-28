package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.event.CorporationTypeUpdateEvent;

@Named
@Scope("view")
public class CorporationTypeController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private CorporationTypeDTO corporationType;

	public void save() {
		try {
			syncEventPublisher.publish(new CorporationTypeUpdateEvent(
					getCurrentOperator().getNodeId(), getCorporationType()));
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public CorporationTypeDTO getCorporationType() {
		if (corporationType != null)
			return corporationType;
		corporationType = nodeId == null ? new CorporationTypeDTO()
				: queryService.findOneCorporationType(nodeId);
		return corporationType;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setCorporationType(CorporationTypeDTO corporationType) {
		this.corporationType = corporationType;
	}
}
