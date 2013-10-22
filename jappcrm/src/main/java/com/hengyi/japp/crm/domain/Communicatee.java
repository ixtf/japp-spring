package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Communicatee extends Modifiable implements Serializable {
	private static final long serialVersionUID = 3570510694131593628L;
	@NotBlank
	@Indexed
	private String name;
	private String phone;
	@Email
	private String email;
	private String fax;
	@Indexed
	private String note;

	public String getName() {
		return name;
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

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public void setPhone(String phone) {
		this.phone = StringUtils.trim(phone);
	}

	public void setEmail(String email) {
		this.email = StringUtils.trim(email);
	}

	public void setFax(String fax) {
		this.fax = StringUtils.trim(fax);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return getName() + " | " + getPhone();
	}
}
