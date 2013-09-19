package com.hengyi.japp.personalevaluation.domain.data;

import java.io.Serializable;
import java.util.Date;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.personalevaluation.domain.node.Operator;

public class Modifiable extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -492338968834947461L;
	@RelatedTo(type = Operator.OPERATOR_CREATOR, direction = Direction.INCOMING)
	@Fetch
	protected Operator creator;
	protected Date createTime;
	@RelatedTo(type = Operator.OPERATOR_MODIFIER, direction = Direction.INCOMING)
	@Fetch
	protected Operator modifier;
	protected Date modifyTime;

	public void setOperator(Operator operator) {
		if (operator.getUuid().equals(CommonConstant.ADMIN_PRINCIPAL))
			return;
		this.modifier = operator;
		this.modifyTime = new Date();
		if (creator == null) {
			this.creator = operator;
			this.createTime = new Date();
		}
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

	public Modifiable() {
		super();
		// TODO Auto-generated constructor stub
	}
}
