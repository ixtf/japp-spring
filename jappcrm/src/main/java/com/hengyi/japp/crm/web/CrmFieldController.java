package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.CrmField;

@Named
@Scope("request")
public class CrmFieldController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -8123684444056593587L;
	private Long nodeId;
	private CrmField crmField;

	public void save() {
		try {
			crmFieldService.save(getCrmField());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public CrmField getCrmField() {
		if (crmField != null)
			return crmField;
		if (nodeId == null)
			crmField = new CrmField();
		else
			crmField = crmFieldService.findOne(nodeId);
		return crmField;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setCrmField(CrmField corporationType) {
		this.crmField = corporationType;
	}
}
