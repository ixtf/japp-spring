package com.hengyi.japp.common.sap.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "LipsPK")
public class LipsPK implements ValueObject<LipsPK> {
	private static final long serialVersionUID = 91678506001070459L;
	@NotBlank
	@Column(nullable = false, updatable = false, length = 10)
	private String vbeln;
	@NotBlank
	@Column(nullable = false, updatable = false, length = 6)
	private String posnr;

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

	public LipsPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LipsPK(String vbeln, String posnr) {
		super();
		this.vbeln = vbeln;
		this.posnr = posnr;
	}

	@Override
	public boolean sameValueAs(LipsPK other) {
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
		final LipsPK other = (LipsPK) o;
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
