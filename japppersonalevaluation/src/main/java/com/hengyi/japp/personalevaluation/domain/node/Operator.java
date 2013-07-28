package com.hengyi.japp.personalevaluation.domain.node;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public class Operator extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = 8369098488832226673L;
	public static final String OPERATOR_PERSON = "AS";
	public static final String OPERATOR_TASK = "CHARGE";
	public static final String OPERATOR_CREATOR = "CREATOR";
	public static final String OPERATOR_MODIFIER = "MODIFIER";
	public static final String NAME_SEARCH = "operatorNameSearch";
	public static final String EMPSN_SEARCH = "operatorEmpSnSearch";
	@NotBlank
	@Indexed(unique = true)
	private String uuid;
	@NotBlank
	@Indexed(indexType = IndexType.FULLTEXT, indexName = NAME_SEARCH)
	private String name;
	@NotBlank
	@Size(min = 8, max = 8)
	@Pattern(regexp = "^[1-9]\\d{7}$")
	@Indexed(indexType = IndexType.SIMPLE, indexName = EMPSN_SEARCH)
	private String empSn;
	private Long lastTaskNodeId;

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public String getEmpSn() {
		return empSn;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmpSn(String empSn) {
		this.empSn = empSn;
	}

	public Long getLastTaskNodeId() {
		return lastTaskNodeId;
	}

	public void setLastTaskNodeId(Long lastTaskNodeId) {
		this.lastTaskNodeId = lastTaskNodeId;
	}

	public Operator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operator(String uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}
}
