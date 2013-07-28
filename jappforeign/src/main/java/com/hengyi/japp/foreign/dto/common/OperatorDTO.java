package com.hengyi.japp.foreign.dto.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "OperatorDTO")
public class OperatorDTO implements Serializable {
	private static final long serialVersionUID = 1110592833181547835L;
	protected String uuid;
	protected String name;

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setName(String name) {
		this.name = name;
	}
}
