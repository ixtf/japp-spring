package com.hengyi.japp.crm.domain.node.customer.indicator;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

/**
 * 经营稳定性
 */
@NodeEntity
public class OperateStability extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 6364232419728827165L;
	private static final Double percent = 7.0;
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
		this.name = StringUtils.trim(name);
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public static Double getPercent() {
		return percent;
	}
}