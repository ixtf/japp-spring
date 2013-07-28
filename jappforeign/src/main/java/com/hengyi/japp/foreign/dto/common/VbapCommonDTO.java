package com.hengyi.japp.foreign.dto.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.data.Status;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbapCommonDTO")
public class VbapCommonDTO implements Serializable {
	private static final long serialVersionUID = 6579264331656888415L;
	protected VbapPK pk;
	protected Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public VbapPK getPk() {
		return pk;
	}

	public void setPk(VbapPK pk) {
		this.pk = pk;
	}

}
