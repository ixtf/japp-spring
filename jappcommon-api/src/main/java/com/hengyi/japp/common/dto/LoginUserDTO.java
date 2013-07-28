package com.hengyi.japp.common.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.data.PrincipalType;

@XmlRootElement(namespace = "com.hengyi.japp.commmon", name = "LoginUserDTO")
public class LoginUserDTO extends UserDTO {
	private static final long serialVersionUID = 4512813784974325597L;
	private PrincipalType principalType;
	private String principal;

	public PrincipalType getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(PrincipalType principalType) {
		this.principalType = principalType;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LoginUserDTO other = (LoginUserDTO) o;
		return Objects.equal(getPrincipalType(), other.getPrincipalType())
				&& Objects.equal(getPrincipal(), other.getPrincipal());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getPrincipalType(), getPrincipal());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getPrincipalType())
				.addValue(getPrincipal()).toString();
	}
}
