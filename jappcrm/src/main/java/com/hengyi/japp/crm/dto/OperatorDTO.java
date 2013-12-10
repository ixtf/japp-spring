package com.hengyi.japp.crm.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.hengyi.japp.common.CommonConstant;

public class OperatorDTO extends AbstractDTO implements Serializable {
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
			theme = CommonConstant.DEFAULT_THEME.getName();
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
