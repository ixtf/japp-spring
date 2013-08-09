package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class CommunicateInfo implements Serializable {
	private static final long serialVersionUID = 3104842085602923519L;
	@NotBlank
	@Size(max = 50)
	@Column(length = 50, nullable = false)
	private String addressName;
	@NotBlank
	@Size(max = 10)
	@Column(length = 10, nullable = false)
	private String zipCode;
	@Size(max = 30)
	@Column(length = 30)
	private String phone;
	@Email
	@Size(max = 30)
	@Column(length = 30)
	private String email;
	@Size(max = 30)
	@Column(length = 30)
	private String fax;

	public String getAddressName() {
		return addressName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
}
