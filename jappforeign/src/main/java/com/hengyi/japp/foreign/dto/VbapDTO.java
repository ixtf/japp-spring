package com.hengyi.japp.foreign.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.dto.common.VbapCommonDTO;
import com.hengyi.japp.foreign.dto.common.VbapStockPrepareItemDTO;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "VbapDTO")
public class VbapDTO extends VbapCommonDTO {
	private static final long serialVersionUID = 4465409863337774384L;
	private List<VbapStockPrepareItemDTO> stockPrepareItems;

	public List<VbapStockPrepareItemDTO> getStockPrepareItems() {
		return stockPrepareItems;
	}

	public void setStockPrepareItems(
			List<VbapStockPrepareItemDTO> stockPrepareItems) {
		this.stockPrepareItems = stockPrepareItems;
	}

}