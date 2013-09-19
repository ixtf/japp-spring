package com.hengyi.japp.trans.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractUuid;
import com.hengyi.japp.trans.domain.data.Meins;
import com.hengyi.japp.trans.domain.data.RecordType;

@Indexed
@MappedSuperclass
public abstract class Record extends AbstractUuid implements Serializable {
	private static final long serialVersionUID = 5415402268926356228L;
	@Field
	@NotBlank
	@Column(nullable = false, length = 20)
	protected String number;
	@NotNull
	@Future
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	protected Date recordDate;
	@NotBlank
	@Column(nullable = false, length = 10)
	protected String carNo;
	@NotBlank
	@Column(nullable = false, length = 20)
	protected String matnr;
	@NotBlank
	@Column(nullable = false, length = 30)
	protected String batchNo;
	@NotNull
	@Min(0)
	@Column(nullable = false)
	protected BigDecimal amount1;
	@NotNull
	@Min(0)
	@Column(nullable = false)
	protected BigDecimal amount2;
	@NotNull
	@Min(0)
	@Column(nullable = false)
	protected BigDecimal amount;

	@NotNull
	@Column(nullable = false)
	protected Meins meins;
	@NotNull
	@Min(0)
	@Column(nullable = false, updatable = false)
	protected Integer packageCount = 0;
	@NotBlank
	@Column(nullable = false, length = 30)
	protected String transCorp;
	@IndexedEmbedded
	protected Noteable noteable;

	public abstract RecordType getRecordType();

	public abstract PackType getPackType();

	public abstract TransType getTransType();

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public String getCarNo() {
		return carNo;
	}

	public String getMatnr() {
		return matnr;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public BigDecimal getAmount1() {
		return amount1;
	}

	public BigDecimal getAmount2() {
		return amount2;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Meins getMeins() {
		return meins;
	}

	public Integer getPackageCount() {
		return packageCount;
	}

	public String getTransCorp() {
		return transCorp;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public void setAmount1(BigDecimal amount1) {
		this.amount1 = amount1;
	}

	public void setAmount2(BigDecimal amount2) {
		this.amount2 = amount2;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setMeins(Meins meins) {
		this.meins = meins;
	}

	public void setPackageCount(Integer packageCount) {
		this.packageCount = packageCount;
	}

	public void setTransCorp(String transCorp) {
		this.transCorp = transCorp;
	}

	public void setOperator(Operator operator) {
		getNoteable().setOperator(operator);
	}

	public void setNote(String note) {
		getNoteable().setNote(note);
	}

	public Noteable getNoteable() {
		if (noteable == null)
			noteable = new Noteable();
		return noteable;
	}

	public void setNoteable(Noteable noteable) {
		this.noteable = noteable;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Record other = (Record) o;
		return Objects.equal(getUuid(), other.getUuid());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUuid());
	}
}
