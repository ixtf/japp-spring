package com.hengyi.japp.common.sap.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "Kna1DTO")
public class SapKna1DTO implements Serializable {
	private static final long serialVersionUID = 3923738147429986566L;
	private String kunnr;
	private String land1;
	private String name1;

	public String getKunnr() {
		return kunnr;
	}

	public String getLand1() {
		return land1;
	}

	public String getName1() {
		return name1;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public void setLand1(String land1) {
		this.land1 = land1;
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
		final SapKna1DTO other = (SapKna1DTO) o;
		return new EqualsBuilder().append(getKunnr(), other.getKunnr())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getKunnr()).hashCode();
	}
}
