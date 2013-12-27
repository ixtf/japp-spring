package com.hengyi.japp.common.sap.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "LipsPK")
public class EkpoPK implements ValueObject<EkpoPK> {
	private static final long serialVersionUID = 91678506001070459L;
	@NotBlank
	@Column(nullable = false, updatable = false, length = 10)
	private String ebeln;
	@NotBlank
	@Column(nullable = false, updatable = false, length = 5)
	private String ebelp;

	public String getEbeln() {
		return ebeln;
	}

	public void setEbeln(String ebeln) {
		this.ebeln = ebeln;
	}

	public String getEbelp() {
		return ebelp;
	}

	public void setEbelp(String ebelp) {
		this.ebelp = ebelp;
	}

	public EkpoPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EkpoPK(String ebeln, String ebelp) {
		super();
		this.ebeln = ebeln;
		this.ebelp = ebelp;
	}

	@Override
	public boolean sameValueAs(EkpoPK other) {
		return other != null && Objects.equal(getEbeln(), other.getEbeln())
				&& Objects.equal(getEbelp(), other.getEbelp());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final EkpoPK other = (EkpoPK) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getEbeln(), getEbelp());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getEbeln())
				.addValue(getEbelp()).toString();
	}
}
