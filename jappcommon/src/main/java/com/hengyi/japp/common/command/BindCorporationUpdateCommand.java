package com.hengyi.japp.common.command;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

public class BindCorporationUpdateCommand extends AbstractNeo4j {
	@NotNull
	private BindCorporationType bindCorporationType;
	@NotNull
	@NotEmpty
	private String id;
	private String name;

	public BindCorporationType getBindCorporationType() {
		return bindCorporationType == null ? BindCorporationType.SAP
				: bindCorporationType;
	}

	public String getId() {
		return id;
	}

	public void setBindCorporationType(BindCorporationType bindCorporationType) {
		this.bindCorporationType = bindCorporationType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
