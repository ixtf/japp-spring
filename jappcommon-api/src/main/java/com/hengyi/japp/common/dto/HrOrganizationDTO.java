package com.hengyi.japp.common.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement(namespace = "com.hengyi.japp.common", name = "HrOrganization")
public class HrOrganizationDTO {
	private String id;
	private String name;
	private String pId;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getpId() {
		return pId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HrOrganizationDTO other = (HrOrganizationDTO) o;
		return Objects.equal(getId(), other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId(), getId());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getName()).toString();
	}
}
