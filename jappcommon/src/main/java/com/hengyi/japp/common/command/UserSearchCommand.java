package com.hengyi.japp.common.command;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserSearchCommand {
	@NotNull
	@NotEmpty
	private String name;

	public UserSearchCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSearchCommand(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
