package com.hengyi.japp.common.domain.node.bind.user;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.MyUtil;
import com.sun.istack.NotNull;

@NodeEntity
public abstract class InnerUser extends AbstractBindUser {
	@NotNull
	@NotEmpty
	@Indexed(unique = true)
	protected String principal;
	@NotNull
	@NotEmpty
	@Size(min = 6)
	protected String password;

	public InnerUser() {
		super();
	}

	public InnerUser(String principal, String name) {
		super(principal, name);
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Object getSalt() {
		return MyUtil.salt(getPrincipalType(), getPrincipal());
	}
}
