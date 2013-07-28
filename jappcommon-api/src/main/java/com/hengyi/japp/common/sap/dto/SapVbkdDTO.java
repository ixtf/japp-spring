package com.hengyi.japp.common.sap.dto;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbkdDTO")
public class SapVbkdDTO implements ValueObject<SapVbkdDTO> {
	private static final long serialVersionUID = 6493016891294566911L;
	private VbkdPK pk;
	private String inco1;
	private String zterm;

	public VbkdPK getPk() {
		return pk;
	}

	public String getInco1() {
		return inco1;
	}

	public String getZterm() {
		return zterm;
	}

	public void setZterm(String zterm) {
		this.zterm = zterm;
	}

	public void setPk(VbkdPK pk) {
		this.pk = pk;
	}

	public void setInco1(String inco1) {
		this.inco1 = inco1;
	}

	@Override
	public boolean sameValueAs(SapVbkdDTO other) {
		return other != null
				&& new EqualsBuilder().append(getPk(), other.getPk())
						.isEquals();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SapVbkdDTO other = (SapVbkdDTO) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getPk()).toHashCode();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getPk()).toString();
	}
}
