package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * 已提交资格证
 * */
@NodeEntity
public class Certificate extends Modifiable implements Serializable {
	private static final long serialVersionUID = -214887469969890618L;
	@NotBlank
	@Indexed(unique = true)
	private String name;

	public Certificate() {
		super();
	}

	public Certificate(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	@Override
	public String toString() {
		return getName();
	}
}
