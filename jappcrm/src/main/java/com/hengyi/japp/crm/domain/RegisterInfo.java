package com.hengyi.japp.crm.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;

@Embeddable
public class RegisterInfo implements Serializable {
	private static final long serialVersionUID = -7186731128960517373L;
	@NotBlank
	@Length(max = 30)
	@Column(length = 30)
	private String number;
	@Length(max = 50)
	@Column(length = 50)
	private String place;
	@NotNull
	private BigDecimal capital;

	public String getNumber() {
		return number;
	}

	public String getPlace() {
		return place;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (object == null || getNumber() == null
				|| getClass() != object.getClass())
			return false;
		RegisterInfo other = (RegisterInfo) object;
		return Objects.equal(getNumber(), other.getNumber());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNumber());
	}

	@Override
	public String toString() {
		return getNumber();
	}
}
