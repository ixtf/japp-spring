package com.hengyi.japp.foreign.dto.common;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbapStockPrepareItemDTO")
public class VbapStockPrepareItemDTO extends StockPrepareItemCommonDTO {
	private static final long serialVersionUID = -5429428877949637941L;
	protected StockPrepareCommonDTO stockPrepare;

	public StockPrepareCommonDTO getStockPrepare() {
		return stockPrepare;
	}

	public void setStockPrepare(StockPrepareCommonDTO stockPrepare) {
		this.stockPrepare = stockPrepare;
	}
}
