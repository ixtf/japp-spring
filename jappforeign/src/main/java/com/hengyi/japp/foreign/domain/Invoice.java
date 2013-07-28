package com.hengyi.japp.foreign.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.data.Statusable;
import com.hengyi.japp.foreign.util.StatusUtil;

@Entity
@Table(name = "t_invoice")
public class Invoice extends Noteable implements Statusable {
	private static final long serialVersionUID = -4817181725946101730L;
	@Id
	@Column(name = "invoice_number")
	private String number;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date deliveryDate = new Date();
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date etd = new Date();
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date eta = new Date();
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private InvoiceRecieveInfo recieveInfo;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private InvoiceInsuranceInfo insuranceInfo;
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private Set<Likp> likps;

	public void updateStatus() {
		for (Likp likp : getLikps())
			likp.updateStatus();
	}

	public Status getStatus() {
		Status insuranceStatus = (insuranceInfo != null && insuranceInfo
				.isInsurance()) ? Status.ALL_FINISH : Status.INIT;
		Status recieveStatus = (recieveInfo != null && recieveInfo.isRecieve()) ? Status.ALL_FINISH
				: Status.INIT;
		return StatusUtil.getStatus(insuranceStatus, recieveStatus);
	}

	public boolean bindLikp(Likp likp) {
		likp.setInvoice(this);
		return this.getLikps().add(likp);
	}

	public String getNumber() {
		if (number != null)
			number = StringUtils.trim(number).toUpperCase();
		return number;
	}

	public Set<Likp> getLikps() {
		if (likps == null)
			likps = Sets.newHashSet();
		return likps;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public Date getEtd() {
		return etd;
	}

	public Date getEta() {
		return eta;
	}

	public void setNumber(String number) {
		this.number = StringUtils.trim(number).toUpperCase();
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setEtd(Date etd) {
		this.etd = etd;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public void setLikps(Set<Likp> likps) {
		this.likps = likps;
		for (Likp likp : getLikps())
			likp.setInvoice(this);
	}

	public InvoiceRecieveInfo getRecieveInfo() {
		return recieveInfo;
	}

	public InvoiceInsuranceInfo getInsuranceInfo() {
		return insuranceInfo;
	}

	public void setRecieveInfo(InvoiceRecieveInfo recieveInfo) {
		this.recieveInfo = recieveInfo;
		updateStatus();
	}

	public void setInsuranceInfo(InvoiceInsuranceInfo insuranceInfo) {
		this.insuranceInfo = insuranceInfo;
		updateStatus();
	}

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(String number, Operator operator) {
		this();
		this.setNumber(number);
		this.setOperator(operator);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getNumber()).hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Invoice other = (Invoice) o;
		return new EqualsBuilder().append(getNumber(), other.getNumber())
				.isEquals();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getNumber()).toString();
	}
}
