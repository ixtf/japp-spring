package com.hengyi.japp.trans.domain.ys;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractUuid;
import com.hengyi.japp.trans.domain.Modifiable;

@Entity
@Table(name = "t_ys_pick")
public class YsPick extends AbstractUuid {
	@NotNull
	@Future
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;
	@NotBlank
	@Column(nullable = false, updatable = false, length = 10)
	private String carNo;
	private Modifiable modifiable;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private YsPackType packType;
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private YsTransType transType;

	@OneToMany(mappedBy = "pick", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<YsPickZvbeln> zvbelns = Sets.newHashSet();

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public String getCarNo() {
		return carNo;
	}

	public Modifiable getModifiable() {
		return modifiable;
	}

	public YsPackType getPackType() {
		return packType;
	}

	public YsTransType getTransType() {
		return transType;
	}

	public Set<YsPickZvbeln> getZvbelns() {
		return zvbelns;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public void setModifiable(Modifiable modifiable) {
		this.modifiable = modifiable;
	}

	public void setPackType(YsPackType packType) {
		this.packType = packType;
	}

	public void setTransType(YsTransType transType) {
		this.transType = transType;
	}

	public void setZvbelns(Set<YsPickZvbeln> zvbelns) {
		this.zvbelns = zvbelns;
	}
}
