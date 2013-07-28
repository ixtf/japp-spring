package com.hengyi.japp.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@XmlRootElement(namespace = "com.hengyi.japp.common", name = "Corporation")
public class CorporationDTO extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 7506521158409200576L;
	private String uuid;
	private String shortName;
	private String fullName;

	public String getUuid() {
		return uuid;
	}

	public String getShortName() {
		return shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CorporationDTO other = (CorporationDTO) o;
		return Objects.equal(getUuid(), other.getUuid());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUuid(), getUuid());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getShortName()).toString();
	}
}
