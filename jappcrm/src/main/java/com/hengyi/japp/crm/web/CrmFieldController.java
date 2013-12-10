package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.CorporationType;

@Named
@Scope("request")
public class CrmFieldController extends AbstractController implements
	Serializable {
    private static final long serialVersionUID = -8123684444056593587L;
    private Long nodeId;
    private CorporationType crmType;

    public void save() {
	try {
	    getCrmType().setOperator(getCurrentOperator());
	    crmTypeService.save(getCrmType());
	    operationSuccessMessage();
	} catch (Exception e) {
	    errorMessage(e);
	}
    }

    public CorporationType getCrmType() {
	if (crmType != null)
	    return crmType;
	if (nodeId == null)
	    crmType = new CorporationType();
	else
	    crmType = crmTypeService.findOne(nodeId);
	return crmType;
    }

    public Long getNodeId() {
	return nodeId;
    }

    public void setNodeId(Long nodeId) {
	this.nodeId = nodeId;
    }

    public void setCrmType(CorporationType crmType) {
	this.crmType = crmType;
    }
}
