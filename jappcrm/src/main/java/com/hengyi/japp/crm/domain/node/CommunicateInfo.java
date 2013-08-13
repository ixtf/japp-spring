package com.hengyi.japp.crm.domain.node;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class CommunicateInfo extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 3104842085602923519L;
	@NotBlank
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
		this.name = name;
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
