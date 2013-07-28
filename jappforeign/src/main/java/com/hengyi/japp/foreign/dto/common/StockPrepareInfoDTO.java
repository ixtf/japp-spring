package com.hengyi.japp.foreign.dto.common;

public class StockPrepareInfoDTO extends ModifyInfoDTO {
	private static final long serialVersionUID = 4510343712844824881L;
	protected String number;
	protected boolean finish;

	public String getNumber() {
		return number;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
}
