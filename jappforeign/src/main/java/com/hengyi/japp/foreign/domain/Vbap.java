package com.hengyi.japp.foreign.domain;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.data.Statusable;
import com.hengyi.japp.foreign.util.StatusUtil;

@Entity
@Table(name = "t_vbap")
public class Vbap implements ValueObject<Vbap>, Statusable {
	private static final long serialVersionUID = 8329694709297983310L;
	@EmbeddedId
	private VbapPK pk;
	@MapsId("vbeln")
	@JoinColumn(name = "vbeln")
	@ManyToOne(optional = false)
	private Vbak vbak;
	@NotNull
	private Status status = Status.INIT;
	@OneToMany(mappedBy = "vbap")
	private Set<StockPrepareItem> stockPrepareItems;

	public void updateStatus() {
		setStatus(StatusUtil.getStatus(getStockPrepareItems()));
	}

	public Set<StockPrepareItem> getStockPrepareItems() {
		if (stockPrepareItems == null)
			stockPrepareItems = Sets.newHashSet();
		return stockPrepareItems;
	}

	public VbapPK getPk() {
		return pk;
	}

	public void setStockPrepareItems(Set<StockPrepareItem> stockPrepareItems) {
		this.stockPrepareItems = stockPrepareItems;
	}

	public void setPk(VbapPK pk) {
		this.pk = pk;
	}

	public Status getStatus() {
		if (status == null)
			status = Status.INIT;
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Vbak getVbak() {
		return vbak;
	}

	public void setVbak(Vbak vbak) {
		this.vbak = vbak;
		vbak.getVbaps().add(this);
	}

	public Vbap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vbap(VbapPK pk, Vbak vbak) {
		this();
		setPk(pk);
		setVbak(vbak);
	}

	@Override
	public boolean sameValueAs(Vbap other) {
		return other != null
				&& new EqualsBuilder().append(getPk(), other.getPk())
						.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getPk()).hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Vbap other = (Vbap) o;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getPk()).toString();
	}
}
