package com.hengyi.japp.common.sap.dto;

public class RangeDTO<T> {
	private String sign;
	private String option;
	private T low;
	private T high;

	public String getSign() {
		return sign;
	}

	public T getLow() {
		return low;
	}

	public T getHigh() {
		return high;
	}

	public String getOption() {
		return option;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setLow(T low) {
		this.low = low;
	}

	public void setHigh(T high) {
		this.high = high;
	}

	public void setOption(String option) {
		this.option = option;
	}
}
