package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * 公司类型
 * */
@NodeEntity
public class CorporationType extends Modifiable implements Serializable {
	private static final long serialVersionUID = 6364232419728827165L;
	@NotBlank
	@Indexed(unique = true)
	private String name;

	public CorporationType() {
		super();
	}

	public CorporationType(String name) {
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
