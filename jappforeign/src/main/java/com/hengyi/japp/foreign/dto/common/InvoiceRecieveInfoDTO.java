package com.hengyi.japp.foreign.dto.common;

public class InvoiceRecieveInfoDTO extends ModifyInfoDTO {
	private static final long serialVersionUID = 6579264331656888415L;
	protected String number;
	protected boolean recieve;

	public String getNumber() {
		return number;
	}

	public boolean isRecieve() {
		return recieve;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setRecieve(boolean recieve) {
		this.recieve = recieve;
	}
}