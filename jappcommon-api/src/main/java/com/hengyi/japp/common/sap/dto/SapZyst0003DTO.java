package com.hengyi.japp.common.sap.dto;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.Constant;

@Embeddable
@XmlRootElement(namespace = Constant.NAME_SPACE, name = "Zyst0003DTO")
public class SapZyst0003DTO implements ValueObject<SapZyst0003DTO> {
	private static final long serialVersionUID = -2229137051932773627L;
	private Zyst0003PK pk;
	private String zprno;
	private String name2;
	private String zvbeln;
	private String extenssion1;
	private String zbeiz;
	private String begda;
	private String endda;
	private String px1;
	private String px2;

	public Zyst0003PK getPk() {
		return pk;
	}

	public void setPk(Zyst0003PK pk) {
		this.pk = pk;
	}

	public String getZprno() {
		return zprno;
	}

	public void setZprno(String zprno) {
		this.zprno = zprno;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getZvbeln() {
		return zvbeln;
	}

	public void setZvbeln(String zvbeln) {
		this.zvbeln = zvbeln;
	}

	public String getExtenssion1() {
		return extenssion1;
	}

	public void setExtenssion1(String extenssion1) {
		this.extenssion1 = extenssion1;
	}

	public String getZbeiz() {
		return zbeiz;
	}

	public void setZbeiz(String zbeiz) {
		this.zbeiz = zbeiz;
	}

	public String getBegda() {
		return begda;
	}

	public void setBegda(String begda) {
		this.begda = begda;
	}

	public String getEndda() {
		return endda;
	}

	public void setEndda(String endda) {
		this.endda = endda;
	}

	public String getPx1() {
		return px1;
	}

	public void setPx1(String px1) {
		this.px1 = px1;
	}

	public String getPx2() {
		return px2;
	}

	public void setPx2(String px2) {
		this.px2 = px2;
	}

	@Override
	public boolean sameValueAs(SapZyst0003DTO other) {
		return other != null && getPk().equals(other.getPk());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SapZyst0003DTO other = (SapZyst0003DTO) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return getPk().hashCode();
	}
}
