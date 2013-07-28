package com.hengyi.japp.common.sap.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "Zyst0003PK")
public class Zyst0003PK implements ValueObject<Zyst0003PK> {
	private static final long serialVersionUID = 91678506001070459L;
	@Column(nullable = false, updatable = false, length = 10)
	private String vbeln;

	public String getVbeln() {
		return vbeln;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	@Override
	public boolean sameValueAs(Zyst0003PK other) {
		return other != null && getVbeln().equals(other.getVbeln());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Zyst0003PK other = (Zyst0003PK) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return getVbeln().hashCode();
	}
}
