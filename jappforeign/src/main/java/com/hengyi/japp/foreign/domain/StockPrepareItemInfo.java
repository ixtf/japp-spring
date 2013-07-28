package com.hengyi.japp.foreign.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class StockPrepareItemInfo implements Serializable {
	private static final long serialVersionUID = 486859607554639170L;
	@NotNull
	@Min(0)
	@Column(name = "info_kwmeng")
	private BigDecimal kwmeng = BigDecimal.ZERO;

	public BigDecimal getKwmeng() {
		return kwmeng;
	}

	public void setKwmeng(BigDecimal kwmeng) {
		this.kwmeng = kwmeng;
	}

	public StockPrepareItemInfo() {
		super();
	}

	public StockPrepareItemInfo(BigDecimal kwmeng) {
		this();
		this.kwmeng = kwmeng;
	}

}
