package com.hengyi.japp.foreign.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.dto.common.StockPrepareCommonDTO;
import com.hengyi.japp.foreign.dto.common.StockPrepareItemCommonDTO;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "StockPrepareItemDTO")
public class StockPrepareItemDTO extends StockPrepareItemCommonDTO {
	private static final long serialVersionUID = -5429428877949637941L;
	private Status status;
	private StockPrepareCommonDTO stockPrepare;

	public Status getStatus() {
		return status;
	}

	public StockPrepareCommonDTO getStockPrepare() {
		return stockPrepare;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setStockPrepare(StockPrepareCommonDTO stockPrepare) {
		this.stockPrepare = stockPrepare;
	}
}
