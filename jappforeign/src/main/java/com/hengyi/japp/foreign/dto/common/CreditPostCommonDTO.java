package com.hengyi.japp.foreign.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.data.Status;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "CreditPostCommonDTO")
public class CreditPostCommonDTO extends ModifyInfoDTO implements Serializable {
	private static final long serialVersionUID = -1684982927482020183L;
	protected String number;
	protected BigDecimal amount;
	protected String waers;
	protected Status status;
	protected CreditPostRecieveInfoDTO recieveInfo;

	public String getNumber() {
		return number;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getWaers() {
		return waers;
	}

	public Status getStatus() {
		return status;
	}

	public CreditPostRecieveInfoDTO getRecieveInfo() {
		return recieveInfo;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setWaers(String waers) {
		this.waers = waers;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setRecieveInfo(CreditPostRecieveInfoDTO recieveInfo) {
		this.recieveInfo = recieveInfo;
	}
}
