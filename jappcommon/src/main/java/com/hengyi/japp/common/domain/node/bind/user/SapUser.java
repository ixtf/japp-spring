package com.hengyi.japp.common.domain.node.bind.user;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.data.PrincipalType;
import com.sun.istack.NotNull;

@NodeEntity
public class SapUser extends AbstractBindUser {
	@NotNull
	@NotEmpty
	@Indexed(unique = true)
	protected String principal;

	public SapUser() {
		super();
	}

	public SapUser(String principal, String name) {
		super(principal, name);
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Override
	public PrincipalType getPrincipalType() {
		return PrincipalType.SAP;
	}
}
