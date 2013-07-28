package com.hengyi.japp.common.command;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengyi.japp.common.data.BindCorporationType;

public class CorporationBindCommand {
	@NotNull
	@NotEmpty
	private String uuid;
	@NotNull
	private BindCorporationType bindCorporationType;
	@NotNull
	@NotEmpty
	private String id;
	private String name;

	public CorporationBindCommand() {
		super();
	}

	public CorporationBindCommand(String uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public BindCorporationType getBindCorporationType() {
		return bindCorporationType == null ? BindCorporationType.OA1
				: bindCorporationType;
	}

	public String getId() {
		return id;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
