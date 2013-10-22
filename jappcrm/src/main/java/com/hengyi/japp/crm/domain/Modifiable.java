package com.hengyi.japp.crm.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

public abstract class Modifiable extends AbstractNeo4j {
	private static final long serialVersionUID = -7106631570438054132L;
	@RelatedTo(type = "CREATOR")
	@Fetch
	private Operator creator;
	private Date createTime;
	@RelatedTo(type = "MODIFIER")
	@Fetch
	private Operator modifier;
	private Date modifyTime;

	public void setOperator(Operator operator) {
		if (creator == null) {
			creator = operator;
			createTime = new Date();
		}
		modifier = operator;
		modifyTime = new Date();
	}

	public Operator getCreator() {
		return creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Operator getModifier() {
		return modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setCreator(Operator creator) {
		this.creator = creator;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setModifier(Operator modifier) {
		this.modifier = modifier;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
