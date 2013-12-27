package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.CrmField;

@Named
@Scope("view")
public class CrmFieldsController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -8123684444056593587L;
	private List<CrmField> crmFields;
	private CrmField crmField;

	@PostConstruct
	public void init() {
		crmFields = crmFieldService.findAll();
	}

	public void edit() {
		redirect(crmFieldService.getUpdatePath(getCrmField().getNodeId()));
	}

	public void delete() {
		try {
			crmFieldService.delete(getCrmField());
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public List<CrmField> getCrmFields() {
		return crmFields;
	}

	public void setCrmFields(List<CrmField> crmFields) {
		this.crmFields = crmFields;
	}

	public CrmField getCrmField() {
		return crmField;
	}

	public void setCrmField(CrmField crmField) {
		this.crmField = crmField;
	}
}
