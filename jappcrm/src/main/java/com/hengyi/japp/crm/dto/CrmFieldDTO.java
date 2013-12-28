package com.hengyi.japp.crm.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.crm.data.CrmFieldType;

public abstract class CrmFieldDTO extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 1L;
	private CrmFieldType crmFieldType;
	@NotBlank
	private String fieldName;
	@NotBlank
	private String displayName;

	public CrmFieldType getCrmFieldType() {
		if (crmFieldType == null)
			crmFieldType = CrmFieldType.CRM;
		return crmFieldType;
	}

	public void setCrmFieldType(CrmFieldType crmFieldType) {
		this.crmFieldType = crmFieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
