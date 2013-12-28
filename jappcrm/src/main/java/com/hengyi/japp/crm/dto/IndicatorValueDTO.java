package com.hengyi.japp.crm.dto;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

public class IndicatorValueDTO extends ModifiableDTO {
	private static final long serialVersionUID = 1L;
	@NotBlank
	private String name;
	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return getName();
	}
}
