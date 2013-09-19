package com.hengyi.japp.trans.domain.ys;

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
import com.hengyi.japp.common.sap.dto.LipsPK;

@Entity
@Table(name = "t_ys_delivery_lips")
public class YsDeliveryLips extends AbstractUuid implements Serializable {
	private static final long serialVersionUID = 6820780499082232621L;
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, updatable = false)
	private YsDelivery ysDelivery;
	@NotNull
	private LipsPK lipsPK;
	@NotNull
	@Min(0)
	private BigDecimal amount;

	public YsDelivery getYsDelivery() {
		return ysDelivery;
	}

	public LipsPK getLipsPK() {
		return lipsPK;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setYsDelivery(YsDelivery ysDelivery) {
		this.ysDelivery = ysDelivery;
	}

	public void setLipsPK(LipsPK lipsPK) {
		this.lipsPK = lipsPK;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final YsDeliveryLips other = (YsDeliveryLips) o;
		return Objects.equal(getUuid(), other.getUuid());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUuid());
	}

}
