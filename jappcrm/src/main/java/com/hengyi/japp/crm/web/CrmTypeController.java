package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.CrmType;

@Named
@Scope("request")
public class CrmTypeController extends AbstractController implements Serializable{
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private CrmType crmType;

	public void save() {
		try {
			crmTypeRepository.save(crmType);
			redirect(URL.CRMTYPES);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public Long getNodeId() {
		return nodeId;
	}

	public CrmType getCrmType() {
		if (crmType == null)
			crmType = new CrmType();
		return crmType;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
		crmType = crmTypeRepository.findOne(nodeId);
	}

	public void setCrmType(CrmType crmType) {
		this.crmType = crmType;
	}
}
