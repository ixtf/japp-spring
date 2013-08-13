package com.hengyi.japp.crm.domain.node.storage;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public abstract class StorageIndicator extends AbstractNeo4j implements
		Serializable {
	private static final long serialVersionUID = -7860529042416756600L;
	@NotBlank
	@Indexed(unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
