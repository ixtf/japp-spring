package com.hengyi.japp.crm.domain.node.customer.indicator;

import javax.validation.constraints.NotNull;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.node.AbstractIndicator;

/**
 * 公司品牌
 * */
@NodeEntity
public class Brand extends AbstractIndicator {
	private static final long serialVersionUID = 6263883482951400351L;
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
