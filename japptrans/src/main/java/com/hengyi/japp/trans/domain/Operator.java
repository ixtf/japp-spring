package com.hengyi.japp.trans.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.search.annotations.Field;
import org.hibernate.validator.constraints.NotBlank;

import com.hengyi.japp.common.domain.shared.AbstractUuid;

@Entity
public class Operator extends AbstractUuid implements Serializable {
	private static final long serialVersionUID = 4589266519056524456L;
	@Field
	@NotBlank
	@Column(nullable = false, length = 10)
	private String name;
	@Column(length = 20)
	private String theme;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Operator() {
		super();
	}

	public Operator(String uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}
}
