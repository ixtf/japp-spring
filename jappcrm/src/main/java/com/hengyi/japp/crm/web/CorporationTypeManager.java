package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.event.CorporationTypeUpdateEvent;

@Named("corpTypeManager")
@Scope("request")
public class CorporationTypeManager extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private CorporationTypeDTO corporationType;

	@PostConstruct
	public void init() {
		if (getNodeId() == null)
			corporationType = new CorporationTypeDTO();
		else
			corporationType = queryService.findOneCorporationType(getNodeId());
	}

	public void save() {
		try {
			syncEventPublisher.publish(new CorporationTypeUpdateEvent(
					getCorporationType(), getCurrentOperatorNodeId()));
			operationSuccessMessage();
			redirect("/corporationTypes/" + corporationType.getNodeId());
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public CorporationTypeDTO getCorporationType() {
		return corporationType;
	}

	public void setCorporationType(CorporationTypeDTO corporationType) {
		this.corporationType = corporationType;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public CorporationTypeDTO getCrmType() {
		return corporationType;
	}

	public void setCrmType(CorporationTypeDTO crmType) {
		this.corporationType = crmType;
	}
}
