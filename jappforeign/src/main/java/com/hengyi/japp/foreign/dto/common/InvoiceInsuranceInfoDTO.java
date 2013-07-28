package com.hengyi.japp.foreign.dto.common;

public class InvoiceInsuranceInfoDTO extends ModifyInfoDTO {
	private static final long serialVersionUID = 6579264331656888415L;
	protected String number;
	protected boolean insurance;

	public String getNumber() {
		return number;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
}
