package com.hengyi.japp.common.sap.dto;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "SapTvzbtDTO")
public class SapTvzbtDTO implements ValueObject<SapTvzbtDTO> {
	private static final long serialVersionUID = -7992170091085467340L;
	private String spras;
	private String zterm;
	private String vtext;

	public String getSpras() {
		return spras;
	}

	public void setSpras(String spras) {
		this.spras = spras;
	}

	public String getZterm() {
		return zterm;
	}

	public void setZterm(String zterm) {
		this.zterm = zterm;
	}

	public String getVtext() {
		return vtext;
	}

	public void setVtext(String vtext) {
		this.vtext = vtext;
	}

	public SapTvzbtDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean sameValueAs(SapTvzbtDTO other) {
		return other != null
				&& new EqualsBuilder().append(getZterm(), other.getZterm())
						.isEquals();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SapTvzbtDTO other = (SapTvzbtDTO) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getZterm()).toHashCode();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getZterm()).toString();
	}
}
