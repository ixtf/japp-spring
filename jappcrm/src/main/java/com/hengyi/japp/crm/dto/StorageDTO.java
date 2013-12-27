package com.hengyi.japp.crm.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StorageDTO extends CrmDTO {
	private static final long serialVersionUID = 1L;
	@NotNull
	@Min(0)
	private BigDecimal capacity;

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}
}
