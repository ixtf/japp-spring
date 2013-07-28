package com.hengyi.japp.foreign.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractUuid;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.data.Statusable;

@Entity
@Table(name = "t_stockPrepareItem")
public class StockPrepareItem extends AbstractUuid implements Serializable,
		Statusable {
	private static final long serialVersionUID = 1216864979928612806L;
	@NotNull
	@Min(0)
	private BigDecimal kwmeng = BigDecimal.ONE;
	@NotNull
	private String vrkme = "KG";
	@NotNull
	private StockPrepareItemInfo info;
	@ManyToOne(optional = false)
	@JoinColumn(name = "stockPrepare_number")
	private StockPrepare stockPrepare;
	@ManyToOne(optional = false)
	private Vbap vbap;

	public void finish() {
		getInfo().setKwmeng(getKwmeng());
		updateStatus();
	}

	public void finish(BigDecimal kwmeng) {
		kwmeng = kwmeng.compareTo(getKwmeng()) > 0 ? getKwmeng() : kwmeng;
		getInfo().setKwmeng(kwmeng);
		updateStatus();
	}

	public void unfinish() {
		getInfo().setKwmeng(BigDecimal.ZERO);
		updateStatus();
	}

	public void updateStatus() {
		getStockPrepare().updateStatus();
		getVbap().updateStatus();
	}

	public Status getStatus() {
		if (info == null || info.getKwmeng().equals(BigDecimal.ZERO))
			return Status.INIT;
		else if (info.getKwmeng().compareTo(getKwmeng()) >= 0)
			return Status.ALL_FINISH;
		else
			return Status.APART_FINISH;
	}

	public Vbap getVbap() {
		return vbap;
	}

	public void setVbap(Vbap vbap) {
		this.vbap = vbap;
		vbap.updateStatus();
	}

	public BigDecimal getKwmeng() {
		return kwmeng;
	}

	public String getVrkme() {
		return vrkme;
	}

	public void setVrkme(String vrkme) {
		this.vrkme = vrkme;
	}

	public void setKwmeng(BigDecimal kwmeng) {
		this.kwmeng = kwmeng;
	}

	public StockPrepare getStockPrepare() {
		return stockPrepare;
	}

	public StockPrepareItemInfo getInfo() {
		return info;
	}

	public void setStockPrepare(StockPrepare stockPrepare) {
		this.stockPrepare = stockPrepare;
		stockPrepare.getItems().add(this);
		stockPrepare.updateStatus();
	}

	public void setInfo(StockPrepareItemInfo info) {
		this.info = info;
		updateStatus();
	}

	public StockPrepareItem() {
		super();
	}

	public StockPrepareItem(StockPrepare stockPrepare, Vbap vbap) {
		this();
		this.setStockPrepare(stockPrepare);
		this.setVbap(vbap);
		this.setInfo(new StockPrepareItemInfo());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUuid());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final StockPrepareItem other = (StockPrepareItem) o;
		return Objects.equal(getUuid(), other.getUuid());
	}
}
