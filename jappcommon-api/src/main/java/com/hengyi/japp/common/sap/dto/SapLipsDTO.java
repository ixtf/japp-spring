package com.hengyi.japp.common.sap.dto;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "LipsDTO")
public class SapLipsDTO implements ValueObject<SapLipsDTO> {
	private static final long serialVersionUID = 2595418267567514743L;
	private LipsPK pk;
	private BigDecimal lfimg;
	private String vrkme;
	private String matnr;
	private String arktx;
	private String vgbel;
	private String vgpos;

	public String getMatnr() {
		return matnr;
	}

	public String getArktx() {
		return arktx;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public void setArktx(String arktx) {
		this.arktx = arktx;
	}

	public BigDecimal getLfimg() {
		return lfimg;
	}

	public String getVrkme() {
		return vrkme;
	}

	public void setLfimg(BigDecimal lfimg) {
		this.lfimg = lfimg;
	}

	public void setVrkme(String vrkme) {
		this.vrkme = vrkme;
	}

	public LipsPK getPk() {
		return pk;
	}

	public void setPk(LipsPK pk) {
		this.pk = pk;
	}

	public String getVgbel() {
		return vgbel;
	}

	public String getVgpos() {
		return vgpos;
	}

	public void setVgbel(String vgbel) {
		this.vgbel = vgbel;
	}

	public void setVgpos(String vgpos) {
		this.vgpos = vgpos;
	}

	@Override
	public boolean sameValueAs(SapLipsDTO other) {
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
		final SapLipsDTO other = (SapLipsDTO) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getPk()).hashCode();
	}
}
