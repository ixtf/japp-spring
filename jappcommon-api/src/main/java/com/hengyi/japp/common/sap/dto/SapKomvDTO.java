package com.hengyi.japp.common.sap.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "LikpDTO")
public class SapKomvDTO implements ValueObject<SapKomvDTO> {
	private static final long serialVersionUID = 3503218953291568743L;
	private String knumv;
	private String kposn;
	private String stunr;
	private String zaehk;
	private String kappl;
	private String kschl;
	private Date kdatu;
	private String krech;
	private BigDecimal kawrt;
	private BigDecimal kbetr;
	private String waers;
	private BigDecimal kkurs;
	private BigDecimal kpein;
	private String kmein;

	public String getKnumv() {
		return knumv;
	}

	public String getKposn() {
		return kposn;
	}

	public String getStunr() {
		return stunr;
	}

	public String getZaehk() {
		return zaehk;
	}

	public String getKappl() {
		return kappl;
	}

	public String getKschl() {
		return kschl;
	}

	public Date getKdatu() {
		return kdatu;
	}

	public String getKrech() {
		return krech;
	}

	public BigDecimal getKawrt() {
		return kawrt;
	}

	public BigDecimal getKkurs() {
		return kkurs;
	}

	public BigDecimal getKpein() {
		return kpein;
	}

	public String getKmein() {
		return kmein;
	}

	public void setKposn(String kposn) {
		this.kposn = kposn;
	}

	public void setStunr(String stunr) {
		this.stunr = stunr;
	}

	public void setZaehk(String zaehk) {
		this.zaehk = zaehk;
	}

	public void setKappl(String kappl) {
		this.kappl = kappl;
	}

	public void setKschl(String kschl) {
		this.kschl = kschl;
	}

	public void setKdatu(Date kdatu) {
		this.kdatu = kdatu;
	}

	public void setKrech(String krech) {
		this.krech = krech;
	}

	public void setKawrt(BigDecimal kawrt) {
		this.kawrt = kawrt;
	}

	public void setKkurs(BigDecimal kkurs) {
		this.kkurs = kkurs;
	}

	public void setKpein(BigDecimal kpein) {
		this.kpein = kpein;
	}

	public void setKmein(String kmein) {
		this.kmein = kmein;
	}

	public String getWaers() {
		return waers;
	}

	public void setKnumv(String knumv) {
		this.knumv = knumv;
	}

	public void setWaers(String waers) {
		this.waers = waers;
	}

	public BigDecimal getKbetr() {
		return kbetr;
	}

	public void setKbetr(BigDecimal kbetr) {
		this.kbetr = kbetr;
	}

	@Override
	public boolean sameValueAs(SapKomvDTO other) {
		// return other != null
		// && new EqualsBuilder().append(getKnumv(), other.getKnumv())
		// .isEquals();
		return false;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SapKomvDTO other = (SapKomvDTO) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getKnumv()).toHashCode();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getKnumv()).toString();
	}
}
