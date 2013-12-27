package com.hengyi.japp.common.sap.dto;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbapDTO")
public class SapVbapDTO implements ValueObject<SapVbapDTO> {
	private static final long serialVersionUID = 2595418267567514743L;
	private VbapPK pk;
	private String matnr;
	private String matkl;
	private String arktx;
	private String pstyv;
	private BigDecimal netwr;
	private String waerk;
	private BigDecimal kwmeng;
	private BigDecimal lsmeng;
	private BigDecimal kbmeng;
	private BigDecimal klmeng;
	private String vrkme;
	private BigDecimal umvkz;
	private BigDecimal umvkn;
	private BigDecimal brgew;
	private BigDecimal ntgew;
	private String gewei;
	private BigDecimal volum;
	private String voleh;

	private String werks;
	private String lgort;
	private String vstel;

	public String getVbeln() {
		return pk == null ? null : pk.getVbeln();
	}

	public void setVbeln(String vbeln) {
		pk = pk == null ? new VbapPK() : pk;
		pk.setVbeln(vbeln);
	}

	public String getPosnr() {
		return pk == null ? null : pk.getPosnr();
	}

	public void setPosnr(String posnr) {
		pk = pk == null ? new VbapPK() : pk;
		pk.setPosnr(posnr);
	}

	public VbapPK getPk() {
		return pk;
	}

	public void setPk(VbapPK pk) {
		this.pk = pk;
	}

	public String getMatnr() {
		return matnr;
	}

	public String getMatkl() {
		return matkl;
	}

	public String getArktx() {
		return arktx;
	}

	public String getPstyv() {
		return pstyv;
	}

	public BigDecimal getNetwr() {
		return netwr;
	}

	public String getWaerk() {
		return waerk;
	}

	public BigDecimal getKwmeng() {
		return kwmeng;
	}

	public BigDecimal getLsmeng() {
		return lsmeng;
	}

	public BigDecimal getKbmeng() {
		return kbmeng;
	}

	public BigDecimal getKlmeng() {
		return klmeng;
	}

	public String getVrkme() {
		return vrkme;
	}

	public BigDecimal getUmvkz() {
		return umvkz;
	}

	public BigDecimal getUmvkn() {
		return umvkn;
	}

	public BigDecimal getBrgew() {
		return brgew;
	}

	public BigDecimal getNtgew() {
		return ntgew;
	}

	public String getGewei() {
		return gewei;
	}

	public BigDecimal getVolum() {
		return volum;
	}

	public String getVoleh() {
		return voleh;
	}

	public String getWerks() {
		return werks;
	}

	public String getLgort() {
		return lgort;
	}

	public String getVstel() {
		return vstel;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public void setMatkl(String matkl) {
		this.matkl = matkl;
	}

	public void setArktx(String arktx) {
		this.arktx = arktx;
	}

	public void setPstyv(String pstyv) {
		this.pstyv = pstyv;
	}

	public void setNetwr(BigDecimal netwr) {
		this.netwr = netwr;
	}

	public void setWaerk(String waerk) {
		this.waerk = waerk;
	}

	public void setKwmeng(BigDecimal kwmeng) {
		this.kwmeng = kwmeng;
	}

	public void setLsmeng(BigDecimal lsmeng) {
		this.lsmeng = lsmeng;
	}

	public void setKbmeng(BigDecimal kbmeng) {
		this.kbmeng = kbmeng;
	}

	public void setKlmeng(BigDecimal klmeng) {
		this.klmeng = klmeng;
	}

	public void setVrkme(String vrkme) {
		this.vrkme = vrkme;
	}

	public void setUmvkz(BigDecimal umvkz) {
		this.umvkz = umvkz;
	}

	public void setUmvkn(BigDecimal umvkn) {
		this.umvkn = umvkn;
	}

	public void setBrgew(BigDecimal brgew) {
		this.brgew = brgew;
	}

	public void setNtgew(BigDecimal ntgew) {
		this.ntgew = ntgew;
	}

	public void setGewei(String gewei) {
		this.gewei = gewei;
	}

	public void setVolum(BigDecimal volum) {
		this.volum = volum;
	}

	public void setVoleh(String voleh) {
		this.voleh = voleh;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public void setLgort(String lgort) {
		this.lgort = lgort;
	}

	public void setVstel(String vstel) {
		this.vstel = vstel;
	}

	@Override
	public boolean sameValueAs(SapVbapDTO other) {
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
		final SapVbapDTO other = (SapVbapDTO) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getPk()).hashCode();
	}
}
