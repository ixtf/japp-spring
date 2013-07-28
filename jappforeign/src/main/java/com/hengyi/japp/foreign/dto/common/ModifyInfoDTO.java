package com.hengyi.japp.foreign.dto.common;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import com.hengyi.japp.foreign.Constant;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "ModifiableDTO")
public class ModifyInfoDTO implements Serializable {
	private static final long serialVersionUID = -8256537349539885693L;
	protected OperatorDTO creator;
	protected Date createTime;
	protected OperatorDTO modifier;
	protected Date modifyTime;
	protected String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OperatorDTO getCreator() {
		return creator;
	}

	@XmlSchemaType(name = "dateTime")
	public Date getCreateTime() {
		return createTime;
	}

	public OperatorDTO getModifier() {
		return modifier;
	}

	@XmlSchemaType(name = "dateTime")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setCreator(OperatorDTO creator) {
		this.creator = creator;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setModifier(OperatorDTO modifier) {
		this.modifier = modifier;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
