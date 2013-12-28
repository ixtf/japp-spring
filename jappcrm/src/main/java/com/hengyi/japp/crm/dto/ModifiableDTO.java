package com.hengyi.japp.crm.dto;

import java.util.Date;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

public abstract class ModifiableDTO extends AbstractNeo4j {
	private static final long serialVersionUID = 1L;
	private OperatorDTO creator;
	private Date createTime;
	private OperatorDTO modifier;
	private Date modifyTime;

	public OperatorDTO getCreator() {
		return creator;
	}

	public void setCreator(OperatorDTO creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public OperatorDTO getModifier() {
		return modifier;
	}

	public void setModifier(OperatorDTO modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
