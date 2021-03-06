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
@Table(name = "t_invoiceRecieveInfo")
public class InvoiceRecieveInfo extends Noteable {
	private static final long serialVersionUID = -9222325527252087971L;
	@Id
	@Column(name = "invoice_number")
	private String number;
	private boolean recieve;
	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Invoice invoice;

	public String getNumber() {
		if (number != null)
			number = StringUtils.trim(number).toUpperCase();
		return number;
	}

	public boolean isRecieve() {
		return recieve;
	}

	public void setNumber(String number) {
		this.number = StringUtils.trim(number).toUpperCase();
	}

	public void setRecieve(boolean recieve) {
		this.recieve = recieve;
		getInvoice().updateStatus();
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
		this.setNumber(invoice.getNumber());
		getInvoice().setRecieveInfo(this);
	}

	public InvoiceRecieveInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceRecieveInfo(Invoice invoice, Operator operator) {
		this();
		this.setInvoice(invoice);
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
		final InvoiceRecieveInfo other = (InvoiceRecieveInfo) o;
		return new EqualsBuilder().append(getNumber(), other.getNumber())
				.isEquals();
	}
}
