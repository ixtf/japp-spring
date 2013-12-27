package com.hengyi.japp.crm.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

public abstract class CrmDTO extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotBlank
	protected String name;
	@NotBlank
	protected String registerNumber;
	@NotBlank
	protected String registerPlace;
	@NotNull
	@Past
	protected Date registerDate;
	@NotNull
	@Future
	protected Date registerInvalidDate;
	@NotNull
	@Min(0)
	protected BigDecimal registerCapital;
	@NotBlank
	protected String represent;
	@NotBlank
	protected String addressName;
	protected String lifnr;
	protected String kunnr;
	// @Pattern(regexp = "^[1-9][0-9]{5}$")
	protected String zipCode;
	@NotNull
	@Min(0)
	protected BigDecimal saleIncome;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getRegisterPlace() {
		return registerPlace;
	}

	public void setRegisterPlace(String registerPlace) {
		this.registerPlace = registerPlace;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getRegisterInvalidDate() {
		return registerInvalidDate;
	}

	public void setRegisterInvalidDate(Date registerInvalidDate) {
		this.registerInvalidDate = registerInvalidDate;
	}

	public BigDecimal getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(BigDecimal registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getRepresent() {
		return represent;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getLifnr() {
		return lifnr;
	}

	public void setLifnr(String lifnr) {
		this.lifnr = lifnr;
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public BigDecimal getSaleIncome() {
		return saleIncome;
	}

	public void setSaleIncome(BigDecimal saleIncome) {
		this.saleIncome = saleIncome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
