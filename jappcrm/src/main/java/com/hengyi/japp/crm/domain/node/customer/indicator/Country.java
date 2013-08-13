package com.hengyi.japp.crm.domain.node.customer.indicator;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

/**
 * 国别
 * */
@NodeEntity
public class Country extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -5672801699514188271L;
	@Indexed(unique = true)
	private String name;
	@NotNull
	private Double score;

	public String getName() {
		return name;
	}

	public Double getScore() {
		return score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}