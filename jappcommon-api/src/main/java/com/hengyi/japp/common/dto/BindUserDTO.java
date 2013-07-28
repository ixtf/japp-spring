package com.hengyi.japp.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@XmlRootElement(namespace = "com.hengyi.japp.common", name = "BindUser")
public class BindUserDTO extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -4813575756155372243L;
	private PrincipalType principalType;
	private String principal;
	private String name;
	private UserDTO user;

	public PrincipalType getPrincipalType() {
		return principalType;
	}

	public String getPrincipal() {
		return principal;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setPrincipalType(PrincipalType principalType) {
		this.principalType = principalType;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BindUserDTO other = (BindUserDTO) o;
		return Objects.equal(getPrincipalType(), other.getPrincipalType())
				&& Objects.equal(getPrincipal(), other.getPrincipal());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getPrincipalType(), getPrincipal());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getName()).toString();
	}
}
