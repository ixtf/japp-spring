package com.hengyi.japp.crm.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

/**
 * 公司类型
 * */
@NodeEntity
public class CrmType extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 6364232419728827165L;
	@NotBlank
	@Indexed(unique = true)
	private String name;

	public CrmType() {
		super();
	}

	public CrmType(String name) {
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
