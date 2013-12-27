package com.hengyi.japp.common.sap.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "EkkoDTO")
public class SapEkkoDTO implements Serializable {
	private static final long serialVersionUID = -1692066636500782618L;
	private String ebeln;
	private String bukrs;
	private String bstyp;
	private String bsart;
	private String bsakz;
	private String zterm;
	private String inco1;
	private String inco2;
	private String evrtn;
	private String zhtyf;
	private String loekz;
	private String statu;
	private Date aedat;

	public String getEbeln() {
		return ebeln;
	}

	public void setEbeln(String ebeln) {
		this.ebeln = ebeln;
	}

	public String getBukrs() {
		return bukrs;
	}

	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}

	public String getBstyp() {
		return bstyp;
	}

	public void setBstyp(String bstyp) {
		this.bstyp = bstyp;
	}

	public String getBsart() {
		return bsart;
	}

	public void setBsart(String bsart) {
		this.bsart = bsart;
	}

	public String getBsakz() {
		return bsakz;
	}

	public void setBsakz(String bsakz) {
		this.bsakz = bsakz;
	}

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

	public String getZterm() {
		return zterm;
	}

	public void setZterm(String zterm) {
		this.zterm = zterm;
	}

	public String getInco1() {
		return inco1;
	}

	public void setInco1(String inco1) {
		this.inco1 = inco1;
	}

	public String getInco2() {
		return inco2;
	}

	public void setInco2(String inco2) {
		this.inco2 = inco2;
	}

	public String getEvrtn() {
		return evrtn;
	}

	public void setEvrtn(String evrtn) {
		this.evrtn = evrtn;
	}

	public String getZhtyf() {
		return zhtyf;
	}

	public void setZhtyf(String zhtyf) {
		this.zhtyf = zhtyf;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SapEkkoDTO other = (SapEkkoDTO) o;
		return Objects.equal(getEbeln(), other.getEbeln());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getEbeln());
	}
}
