package com.hengyi.japp.common.sap.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "EkpoDTO")
public class SapEkpoDTO implements Serializable {
	private static final long serialVersionUID = -1692066636500782618L;
	private String ebeln;
	private String ebelp;
	private String loekz;
	private String statu;
	private Date aedat;
	private String matnr;
	private String txz01;
	private String ematn;
	private String bukrs;
	private String werks;
	private String lgort;
	private String matkl;
	private BigDecimal ktmng;
	private BigDecimal menge;
	private String meins;
	private String bprme;
	private BigDecimal bpumz;
	private BigDecimal bpumn;
	private BigDecimal umrez;
	private BigDecimal umren;
	private BigDecimal netpr;
	private String peinh;
	private BigDecimal netwr;
	private String mwskz;

	public String getLoekz() {
		return loekz;
	}

	public void setLoekz(String loekz) {
		this.loekz = loekz;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Date getAedat() {
		return aedat;
	}

	public void setAedat(Date aedat) {
		this.aedat = aedat;
	}

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getTxz01() {
		return txz01;
	}

	public void setTxz01(String txz01) {
		this.txz01 = txz01;
	}

	public String getEmatn() {
		return ematn;
	}

	public void setEmatn(String ematn) {
		this.ematn = ematn;
	}

	public String getBukrs() {
		return bukrs;
	}

	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public String getLgort() {
		return lgort;
	}

	public void setLgort(String lgort) {
		this.lgort = lgort;
	}

	public String getMatkl() {
		return matkl;
	}

	public void setMatkl(String matkl) {
		this.matkl = matkl;
	}

	public BigDecimal getKtmng() {
		return ktmng;
	}

	public void setKtmng(BigDecimal ktmng) {
		this.ktmng = ktmng;
	}

	public BigDecimal getMenge() {
		return menge;
	}

	public void setMenge(BigDecimal menge) {
		this.menge = menge;
	}

	public String getMeins() {
		return meins;
	}

	public void setMeins(String meins) {
		this.meins = meins;
	}

	public String getBprme() {
		return bprme;
	}

	public void setBprme(String bprme) {
		this.bprme = bprme;
	}

	public BigDecimal getBpumz() {
		return bpumz;
	}

	public void setBpumz(BigDecimal bpumz) {
		this.bpumz = bpumz;
	}

	public BigDecimal getBpumn() {
		return bpumn;
	}

	public void setBpumn(BigDecimal bpumn) {
		this.bpumn = bpumn;
	}

	public BigDecimal getUmrez() {
		return umrez;
	}

	public void setUmrez(BigDecimal umrez) {
		this.umrez = umrez;
	}

	public BigDecimal getUmren() {
		return umren;
	}

	public void setUmren(BigDecimal umren) {
		this.umren = umren;
	}

	public BigDecimal getNetpr() {
		return netpr;
	}

	public void setNetpr(BigDecimal netpr) {
		this.netpr = netpr;
	}

	public String getPeinh() {
		return peinh;
	}

	public void setPeinh(String peinh) {
		this.peinh = peinh;
	}

	public BigDecimal getNetwr() {
		return netwr;
	}

	public void setNetwr(BigDecimal netwr) {
		this.netwr = netwr;
	}

	public String getMwskz() {
		return mwskz;
	}

	public void setMwskz(String mwskz) {
		this.mwskz = mwskz;
	}

	public String getEbeln() {
		return ebeln;
	}

	public void setEbeln(String ebeln) {
		this.ebeln = ebeln;
	}

	public String getEbelp() {
		return ebelp;
	}

	public void setEbelp(String ebelp) {
		this.ebelp = ebelp;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SapEkpoDTO other = (SapEkpoDTO) o;
		return Objects.equal(getEbeln(), other.getEbeln())
				&& Objects.equal(getEbelp(), other.getEbelp());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getEbeln(), getEbelp());
	}
}
