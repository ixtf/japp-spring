package com.hengyi.japp.trans.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotBlank;

@MappedSuperclass
public abstract class TransType implements Serializable {
	private static final long serialVersionUID = 2139909812709763891L;
	@Id
	private Integer id;
	@NotBlank
	@Column(length = 20)
	private String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
