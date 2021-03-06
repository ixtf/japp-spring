package com.hengyi.japp.crm.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.crm.Constant;

public class OperatorDTO extends AbstractNeo4j {
	private static final long serialVersionUID = 1L;
	@NotBlank
	private String uuid;
	@NotBlank
	private String name;
	private String theme;

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		if (theme == null)
			theme = Constant.DEFAULT_THEME.getName();
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Override
	public String toString() {
		return getName();
	}
}
