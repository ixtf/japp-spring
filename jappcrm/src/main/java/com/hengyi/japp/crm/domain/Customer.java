package com.hengyi.japp.crm.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.AbstractUuid;

@Entity
@Table(name = "t_customer")
public class Customer extends Modifiable {
	private static final long serialVersionUID = -831678558917732185L;
	@NotBlank
	@Size(max = 50)
	@Column(length = 50, nullable = false)
	private String name;
	@NotBlank
	@Size(max = 10)
	@Column(length = 10, nullable = false)
	private String represent;
	private RegisterInfo registerInfo;
	private CommunicateInfo communicateInfo;
	private Ownership ownership;
	private boolean policyBusiness;
	private BigDecimal saleRevenue;
	private int durationYears;

	public String getName() {
		return name;
	}

	public String getRepresent() {
		return represent;
	}

	public RegisterInfo getRegisterInfo() {
		if (registerInfo == null)
			registerInfo = new RegisterInfo();
		return registerInfo;
	}

	public CommunicateInfo getCommunicateInfo() {
		if (communicateInfo == null)
			communicateInfo = new CommunicateInfo();
		return communicateInfo;
	}

	public boolean isPolicyBusiness() {
		return policyBusiness;
	}

	public BigDecimal getSaleRevenue() {
		return saleRevenue;
	}

	public int getDurationYears() {
		return durationYears;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
	}

	public void setRegisterInfo(RegisterInfo registerInfo) {
		this.registerInfo = registerInfo;
	}

	public void setCommunicateInfo(CommunicateInfo communicateInfo) {
		this.communicateInfo = communicateInfo;
	}

	public Ownership getOwnership() {
		return ownership;
	}

	public void setOwnership(Ownership ownership) {
		this.ownership = ownership;
	}

	public void setPolicyBusiness(boolean policyBusiness) {
		this.policyBusiness = policyBusiness;
	}

	public void setSaleRevenue(BigDecimal saleRevenue) {
		this.saleRevenue = saleRevenue;
	}

	public void setDurationYears(int durationYears) {
		this.durationYears = durationYears;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	protected String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (object == null || getUuid() == null
				|| getClass() != object.getClass())
			return false;
		AbstractUuid other = (AbstractUuid) object;
		return Objects.equal(getUuid(), other.getUuid());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUuid());
	}
}
