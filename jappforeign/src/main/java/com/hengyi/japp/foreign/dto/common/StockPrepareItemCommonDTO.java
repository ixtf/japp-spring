package com.hengyi.japp.foreign.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import com.hengyi.japp.common.domain.shared.AbstractUuid;
import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.domain.data.Status;

@XmlRootElement(namespace = Constant.NAME_SPACE, name = "StockPrepareItemCommonDTO")
public class StockPrepareItemCommonDTO extends AbstractUuid implements
		Serializable {
	private static final long serialVersionUID = -5429428877949637941L;
	protected BigDecimal kwmeng;
	protected String vrkme;
	protected Status status;
	protected StockPrepareItemInfoDTO info;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getKwmeng() {
		return kwmeng;
	}

	public String getVrkme() {
		return vrkme;
	}

	public StockPrepareItemInfoDTO getInfo() {
		return info;
	}

	public void setKwmeng(BigDecimal kwmeng) {
		this.kwmeng = kwmeng;
	}

	public void setVrkme(String vrkme) {
		this.vrkme = vrkme;
	}

	public void setInfo(StockPrepareItemInfoDTO info) {
		this.info = info;
	}
}
