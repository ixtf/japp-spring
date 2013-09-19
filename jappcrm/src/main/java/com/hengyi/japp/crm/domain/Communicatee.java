package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class Communicatee extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 3570510694131593628L;
	@NotBlank
	@Indexed
	private String name;
	private String phone;
	@Email
	private String email;
	private String fax;

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

	@Override
	public String toString() {
		return getName();
	}
}
