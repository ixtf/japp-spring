package com.hengyi.japp.trans.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Embeddable
@MappedSuperclass
public class Modifiable implements Serializable {
	private static final long serialVersionUID = -492338968834947461L;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	protected Operator creator;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	protected Date createTime;
	@ManyToOne
	protected Operator modifier;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifyTime;

	public void setOperator(Operator operator) {
		setCreator(operator);
		setModifier(operator);
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
		setCreateTime(new Date());
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setModifier(Operator modifier) {
		this.modifier = modifier;
		setModifyTime(new Date());
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Modifiable() {
		super();
		// TODO Auto-generated constructor stub
	}
}
