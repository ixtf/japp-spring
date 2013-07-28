package com.hengyi.japp.foreign.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.data.Statusable;
import com.hengyi.japp.foreign.util.StatusUtil;

@Entity
@Table(name = "t_stockPrepare")
public class StockPrepare extends Noteable implements Statusable {
	private static final long serialVersionUID = -5544456478646025492L;
	@Id
	@Column(name = "stockPrepare_number")
	private String number;
	@NotNull
	private Status status = Status.INIT;
	@OneToMany(mappedBy = "stockPrepare", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<StockPrepareItem> items;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private StockPrepareInfo info;

	public void updateStatus() {
		setStatus(StatusUtil.getStatus(getItems()));
	}																																																											

	public Status getStatus() {
		if (status == null)
			status = Status.INIT;
		return status;
	}

	public String getNumber() {
		if (number != null)
			number = StringUtils.trim(number).toUpperCase();
		return number;
	}

	public Set<StockPrepareItem> getItems() {
		if (items == null)
			items = Sets.newHashSet();
		return items;
	}

	public StockPrepareInfo getInfo() {
		return info;
	}

	public void setItems(Set<StockPrepareItem> items) {
		this.items = items;
	}

	public void setInfo(StockPrepareInfo info) {
		this.info = info;
	}

	public void setNumber(String number) {
		this.number = StringUtils.trim(number).toUpperCase();
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public StockPrepare() {
		super();
	}

	public StockPrepare(String number) {
		this();
		this.setNumber(number);
	}

	public StockPrepare(String number, Operator operator) {
		this(number);
		this.setOperator(operator);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNumber());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final StockPrepare other = (StockPrepare) o;
		return Objects.equal(getNumber(), other.getNumber());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getNumber()).toString();
	}

}
