package com.hengyi.japp.common.sap.dto;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;

@Embeddable
@XmlRootElement(namespace = "com.hengyi.sap", name = "Kna1PK")
public class Kna1PK implements ValueObject<Kna1PK> {
	private static final long serialVersionUID = -9149585781823755962L;
	private String kunnr;

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	@Override
	public boolean sameValueAs(Kna1PK other) {
		return other != null && Objects.equal(getKunnr(), other.getKunnr());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Kna1PK other = (Kna1PK) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getKunnr());
	}
}
