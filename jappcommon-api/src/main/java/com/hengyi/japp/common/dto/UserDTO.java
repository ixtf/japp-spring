package com.hengyi.japp.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@XmlRootElement(namespace = "com.hengyi.japp.common", name = "UserDTO")
public class UserDTO extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -2313331503846592124L;
	protected String uuid;
	protected String name;

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

		UserDTO other = (UserDTO) o;
		return Objects.equal(getUuid(), other.getUuid());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUuid(), getUuid());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getName()).toString();
	}

}
