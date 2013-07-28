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
@Table(name = "t_creditPostRecieveInfo")
public class CreditPostRecieveInfo extends Noteable {
	private static final long serialVersionUID = 4430072867977670450L;
	@Id
	@Column(name = "creditPost_number")
	private String number;
	private boolean recieve;
	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	private CreditPost creditPost;

	public String getNumber() {
		if (number != null)
			number = StringUtils.trim(number).toUpperCase();
		return number;
	}

	public boolean isRecieve() {
		return recieve;
	}

	public void setRecieve(boolean recieve) {
		this.recieve = recieve;
		getCreditPost().updateStatus();
	}

	public void setNumber(String number) {
		this.number = StringUtils.trim(number).toUpperCase();
	}

	public CreditPost getCreditPost() {
		return creditPost;
	}

	public void setCreditPost(CreditPost creditPost) {
		this.creditPost = creditPost;
		creditPost.setRecieveInfo(this);
	}

	public CreditPostRecieveInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	private CreditPostRecieveInfo(String number) {
		this();
		this.setNumber(number);
	}

	public CreditPostRecieveInfo(CreditPost creditPost) {
		this(creditPost.getNumber());
		this.setCreditPost(creditPost);
	}

	public CreditPostRecieveInfo(CreditPost creditPost, Operator operator) {
		this(creditPost);
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
		final CreditPostRecieveInfo other = (CreditPostRecieveInfo) o;
		return new EqualsBuilder().append(getNumber(), other.getNumber())
				.isEquals();
	}
}
