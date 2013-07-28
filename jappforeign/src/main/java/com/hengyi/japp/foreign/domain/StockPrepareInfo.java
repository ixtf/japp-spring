package com.hengyi.japp.foreign.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "t_stockPrepareInfo")
public class StockPrepareInfo extends Noteable {
	private static final long serialVersionUID = -7137312639201435856L;
	@Id
	@Column(name = "stockPrepare_number")
	private String number;
	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	private StockPrepare stockPrepare;

	public String getNumber() {
		if (number != null)
			number = StringUtils.trim(number).toUpperCase();
		return number;
	}

	public void setNumber(String number) {
		this.number = StringUtils.trim(number).toUpperCase();
	}

	public StockPrepare getStockPrepare() {
		return stockPrepare;
	}

	public void setStockPrepare(StockPrepare stockPrepare) {
		this.stockPrepare = stockPrepare;
		stockPrepare.setInfo(this);
		setNumber(stockPrepare.getNumber());
	}

	public StockPrepareInfo() {
		super();
	}

	public StockPrepareInfo(StockPrepare stockPrepare, Operator operator) {
		this.setStockPrepare(stockPrepare);
		this.setOperator(operator);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getNumber()).hashCode();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final StockPrepareInfo other = (StockPrepareInfo) o;
		return new EqualsBuilder().append(getNumber(), other.getNumber())
				.isEquals();
	}

}
