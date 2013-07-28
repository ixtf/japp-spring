package com.hengyi.japp.foreign.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.dto.common.StockPrepareCommonDTO;
import com.hengyi.japp.foreign.dto.common.StockPrepareItemCommonDTO;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "StockPrepareDTO")
public class StockPrepareDTO extends StockPrepareCommonDTO {
	private static final long serialVersionUID = -5429428877949637941L;
	private List<StockPrepareItemCommonDTO> items;

	public List<StockPrepareItemCommonDTO> getItems() {
		return items;
	}

	public void setItems(List<StockPrepareItemCommonDTO> items) {
		this.items = items;
	}
}
