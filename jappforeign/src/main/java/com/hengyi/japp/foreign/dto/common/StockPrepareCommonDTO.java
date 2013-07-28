package com.hengyi.japp.foreign.dto.common;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.data.Status;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "StockPrepareCommonDTO")
public class StockPrepareCommonDTO extends ModifyInfoDTO {
	private static final long serialVersionUID = -5429428877949637941L;
	protected String number;
	protected Status status;
	protected StockPrepareInfoDTO info;

	public String getNumber() {
		return number;
	}

	public StockPrepareInfoDTO getInfo() {
		return info;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setInfo(StockPrepareInfoDTO info) {
		this.info = info;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
