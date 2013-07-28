package com.hengyi.japp.foreign.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.data.Statusable;
import com.sun.istack.NotNull;

@Entity
@Table(name = "t_likp")
public class Likp implements Serializable, Statusable {
	private static final long serialVersionUID = 3824205672328578225L;
	@Id
	@Column(length = 10)
	private String vbeln;
	@NotNull
	private Status status = Status.INIT;
	@OneToMany(mappedBy = "likp", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Lips> lipss;
	@ManyToOne
	private Invoice invoice;

	public void updateStatus() {
		setStatus(invoice == null ? Status.INIT : invoice.getStatus());
	}

	public String getVbeln() {
		return vbeln;
	}

	public Status getStatus() {
		if (status == null)
			status = Status.INIT;
		return status;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
		updateStatus();
	}

	public Set<Lips> getLipss() {
		if (lipss == null)
			lipss = Sets.newHashSet();
		return lipss;
	}

	public void setLipss(Set<Lips> lipss) {
		this.lipss = lipss;
	}

	public Likp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Likp(String vbeln) {
		this();
		this.vbeln = vbeln;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getVbeln());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Likp other = (Likp) o;
		return Objects.equal(getVbeln(), other.getVbeln());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getVbeln()).toString();
	}
}
