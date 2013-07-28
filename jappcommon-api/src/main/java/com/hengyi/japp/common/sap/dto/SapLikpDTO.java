package com.hengyi.japp.common.sap.dto;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "LikpDTO")
public class SapLikpDTO implements ValueObject<SapLikpDTO> {
	private static final long serialVersionUID = 3503218953291568743L;
	private String vbeln;

	public String getVbeln() {
		return vbeln;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	public SapLikpDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SapLikpDTO(String vbeln) {
		super();
		this.vbeln = vbeln;
	}

	@Override
	public boolean sameValueAs(SapLikpDTO other) {
		return other != null
				&& new EqualsBuilder().append(getVbeln(), other.getVbeln())
						.isEquals();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SapLikpDTO other = (SapLikpDTO) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getVbeln()).toHashCode();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getVbeln()).toString();
	}
}
