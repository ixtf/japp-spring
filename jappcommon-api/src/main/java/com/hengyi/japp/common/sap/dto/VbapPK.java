package com.hengyi.japp.common.sap.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbapPK")
public class VbapPK implements ValueObject<VbapPK> {
	private static final long serialVersionUID = 91678506001070459L;
	@NotNull
	@NotEmpty
	@Column(nullable = false, updatable = false, length = 10)
	protected String vbeln;
	@NotNull
	@NotEmpty
	@Column(nullable = false, updatable = false, length = 6)
	protected String posnr;

	public String getVbeln() {
		return vbeln;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = posnr;
	}

	public VbapPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VbapPK(String vbeln, String posnr) {
		super();
		this.vbeln = vbeln;
		this.posnr = posnr;
	}

	@Override
	public boolean sameValueAs(VbapPK other) {
		return other != null
				&& new EqualsBuilder().append(getVbeln(), other.getVbeln())
						.append(getPosnr(), other.getPosnr()).isEquals();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final VbapPK other = (VbapPK) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getVbeln()).append(getPosnr())
				.toHashCode();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(vbeln).addValue(posnr)
				.toString();
	}
}
