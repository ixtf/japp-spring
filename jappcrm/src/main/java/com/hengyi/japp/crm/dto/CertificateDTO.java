package com.hengyi.japp.crm.dto;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

public class CertificateDTO extends ModifiableDTO {
	private static final long serialVersionUID = 1L;
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	@Override
	public String toString() {
		return getName();
	}
}
