package com.hengyi.japp.foreign.dto.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.data.Status;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbakCommonDTO")
public class VbakCommonDTO implements Serializable {
	private static final long serialVersionUID = 6579264331656888415L;
	protected String vbeln;
	protected Status status;

	public String getVbeln() {
		return vbeln;
	}

	public Status getStatus() {
		return status;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
