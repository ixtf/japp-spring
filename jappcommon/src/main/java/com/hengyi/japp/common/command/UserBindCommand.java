package com.hengyi.japp.common.command;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengyi.japp.common.data.PrincipalType;

public class UserBindCommand {
	@NotNull
	@NotEmpty
	private String uuid;
	@NotNull
	private PrincipalType principalType;
	@NotNull
	@NotEmpty
	private String principal;
	private String name;

	public UserBindCommand() {
		super();
	}

	public UserBindCommand(String uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public PrincipalType getPrincipalType() {
		return principalType == null ? PrincipalType.OA1 : principalType;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setPrincipalType(PrincipalType principalType) {
		this.principalType = principalType;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
