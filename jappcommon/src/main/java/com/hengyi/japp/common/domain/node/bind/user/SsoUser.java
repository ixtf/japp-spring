package com.hengyi.japp.common.domain.node.bind.user;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.sun.istack.NotNull;

@NodeEntity
public abstract class SsoUser extends AbstractBindUser {
	@NotNull
	@NotEmpty
	@Indexed(unique = true)
	protected String principal;

	public SsoUser() {
		super();
	}

	public SsoUser(String principal, String name) {
		super(principal, name);
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
}
