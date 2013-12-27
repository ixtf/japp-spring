package com.hengyi.japp.common.sap.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "Lfa1DTO")
public class SapLfa1DTO implements Serializable {
	private static final long serialVersionUID = 3923738147429986566L;
	private String lifnr;
	private String land1;
	private String name1;

	public String getLifnr() {
		return lifnr;
	}

	public void setLifnr(String lifnr) {
		this.lifnr = lifnr;
	}

	public String getLand1() {
		return land1;
	}

	public void setLand1(String land1) {
		this.land1 = land1;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SapLfa1DTO other = (SapLfa1DTO) o;
		return Objects.equal(getLifnr(), other.getLifnr());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getLifnr());
	}
}
