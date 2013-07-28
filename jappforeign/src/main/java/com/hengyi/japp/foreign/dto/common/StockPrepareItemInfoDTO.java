package com.hengyi.japp.foreign.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockPrepareItemInfoDTO implements Serializable {
	private static final long serialVersionUID = -5429428877949637941L;
	protected BigDecimal kwmeng;

	public BigDecimal getKwmeng() {
		return kwmeng;
	}

	public void setKwmeng(BigDecimal kwmeng) {
		this.kwmeng = kwmeng;
	}
}
