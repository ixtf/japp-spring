package com.hengyi.japp.common.sap.dto;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbkdvbDTO")
public class SapVbkdvbDTO implements ValueObject<SapVbkdvbDTO> {
	private static final long serialVersionUID = -1088595725761760865L;
	private VbkdPK pk;
	private String konda;
	private String inco1;
	private String inco2;
	private String zterm;
	private String bstkd;
	private Date bstdk;

	public String getBstkd() {
		return bstkd;
	}

	public Date getBstdk() {
		return bstdk;
	}

	public void setBstkd(String bstkd) {
		this.bstkd = bstkd;
	}

	public void setBstdk(Date bstdk) {
		this.bstdk = bstdk;
	}

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

	public String getKonda() {
		return konda;
	}

	public String getInco2() {
		return inco2;
	}

	public void setKonda(String konda) {
		this.konda = konda;
	}

	public void setInco2(String inco2) {
		this.inco2 = inco2;
	}

	@Override
	public boolean sameValueAs(SapVbkdvbDTO other) {
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
		final SapVbkdvbDTO other = (SapVbkdvbDTO) o;
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
