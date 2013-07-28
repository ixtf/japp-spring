package com.hengyi.japp.foreign.dto.common;

import java.io.Serializable;

public class CreditPostRecieveInfoDTO extends ModifyInfoDTO implements
		Serializable {
	private static final long serialVersionUID = -8022244517466205513L;
	protected String number;
	protected boolean recieve;

	public boolean isRecieve() {
		return recieve;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setRecieve(boolean recieve) {
		this.recieve = recieve;
	}
}