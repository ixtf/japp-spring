package com.hengyi.japp.common.sap.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbakDTO")
public class SapVbakDTO implements ValueObject<SapVbakDTO> {
	private static final long serialVersionUID = 6493016891294566911L;
	private String vbeln;
	private BigDecimal netwr;
	private String waerk;
	private String kunnr;
	private String bstnk;
	private Date audat;

	public String getKunnr() {
		return kunnr;
	}

	public String getVbeln() {
		return vbeln;
	}

	public String getBstnk() {
		return bstnk;
	}

	public Date getAudat() {
		return audat;
	}

	public void setBstnk(String bstnk) {
		this.bstnk = bstnk;
	}

	public void setAudat(Date audat) {
		this.audat = audat;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	public SapVbakDTO(String vbeln) {
		super();
		this.vbeln = vbeln;
	}

	public BigDecimal getNetwr() {
		return netwr;
	}

	public String getWaerk() {
		return waerk;
	}

	public void setNetwr(BigDecimal netwr) {
		this.netwr = netwr;
	}

	public void setWaerk(String waerk) {
		this.waerk = waerk;
	}

	public SapVbakDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean sameValueAs(SapVbakDTO other) {
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
		final SapVbakDTO other = (SapVbakDTO) o;
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
