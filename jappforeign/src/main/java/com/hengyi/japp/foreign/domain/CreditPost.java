package com.hengyi.japp.foreign.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.data.Statusable;

@Entity
@Table(name = "t_creditPost")
public class CreditPost extends Modifiable implements Statusable {
	private static final long serialVersionUID = 4430072867977670450L;
	@Id
	@Column(name = "creditPost_number")
	private String number;
	@NotNull
	@Min(0)
	private BigDecimal amount = BigDecimal.ZERO;
	@NotBlank
	@Column(length = 5)
	private String waers = "USD";
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private CreditPostRecieveInfo recieveInfo;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "t_creditPost_vbak")
	private Set<Vbak> vbaks;

	public void remove(Vbak vbak) {
		getVbaks().remove(vbak);
		vbak.getCreditPosts().remove(this);
		vbak.updateStatus();
	}

	public void add(Vbak vbak) {
		getVbaks().add(vbak);
		vbak.getCreditPosts().add(this);
		vbak.updateStatus();
	}

	public void updateStatus() {
		for (Vbak vbak : getVbaks())
			vbak.updateStatus();
	}

	public Status getStatus() {
		return (recieveInfo != null && recieveInfo.isRecieve()) ? Status.ALL_FINISH
				: Status.INIT;
	}

	public String getWaers() {
		if (waers == null)
			waers = "USD";
		return StringUtils.trim(waers).toUpperCase();
	}

	public String getNumber() {
		if (number != null)
			number = StringUtils.trim(number).toUpperCase();
		return number;
	}

	public Set<Vbak> getVbaks() {
		if (vbaks == null)
			vbaks = Sets.newHashSet();
		return vbaks;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setNumber(String number) {
		this.number = StringUtils.trim(number).toUpperCase();
	}

	public void setVbaks(Set<Vbak> vbaks) {
		this.vbaks = vbaks;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setWaers(String waers) {
		this.waers = StringUtils.trim(waers).toUpperCase();
	}

	public CreditPostRecieveInfo getRecieveInfo() {
		return recieveInfo;
	}

	public void setRecieveInfo(CreditPostRecieveInfo recieveInfo) {
		this.recieveInfo = recieveInfo;
		updateStatus();
	}

	public CreditPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreditPost(String number) {
		this();
		this.setNumber(number);
	}

	public CreditPost(String number, Operator operator) {
		this(number);
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
		final CreditPost other = (CreditPost) o;
		return new EqualsBuilder().append(getNumber(), other.getNumber())
				.isEquals();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getNumber()).toString();
	}
}
