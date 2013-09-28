package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.CrmType;

@Named
@Scope("request")
public class CrmTypeController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private CrmType crmType;

	public void save() {
		try {
			getCrmType().setOperator(getCurrentOperator());
			crmTypeRepository.save(getCrmType());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public CrmType getCrmType() {
		if (crmType != null)
			return crmType;
		if (nodeId == null)
			crmType = new CrmType();
		else
			crmType = crmTypeRepository.findOne(nodeId);
		return crmType;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setCrmType(CrmType crmType) {
		this.crmType = crmType;
	}
}
