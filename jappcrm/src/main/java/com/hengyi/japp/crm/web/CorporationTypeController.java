package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.CorporationType;

@Named
@Scope("request")
public class CorporationTypeController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private CorporationType corporationType;

	public void save() {
		try {
			getCorporationType().setOperator(getCurrentOperator());
			corporationTypeService.save(getCorporationType());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public CorporationType getCorporationType() {
		if (corporationType != null)
			return corporationType;
		if (nodeId == null)
			corporationType = new CorporationType();
		else
			corporationType = corporationTypeService.findOne(nodeId);
		return corporationType;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setCorporationType(CorporationType corporationType) {
		this.corporationType = corporationType;
	}
}
