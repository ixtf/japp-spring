package com.hengyi.japp.foreign.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.data.Statusable;
import com.hengyi.japp.foreign.util.StatusUtil;

@Entity
@Table(name = "t_vbak")
public class Vbak implements Serializable, Statusable {
	private static final long serialVersionUID = -9221579761467982397L;
	@Id
	@Column(length = 10)
	private String vbeln;
	@NotNull
	private Status status = Status.INIT;
	@OneToMany(mappedBy = "vbak", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Vbap> vbaps;
	@ManyToMany(mappedBy = "vbaks")
	private Set<CreditPost> creditPosts;

	public void updateStatus() {
		setStatus(StatusUtil.getStatus(getVbaps(), getCreditPosts()));
	}

	public Set<CreditPost> getCreditPosts() {
		if (creditPosts == null)
			creditPosts = Sets.newHashSet();
		return creditPosts;
	}

	public Status getStatus() {
		if (status == null)
			status = Status.INIT;
		return status;
	}

	public String getVbeln() {
		return vbeln;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCreditPosts(Set<CreditPost> creditPosts) {
		this.creditPosts = creditPosts;
	}

	public Set<Vbap> getVbaps() {
		if (vbaps == null)
			vbaps = Sets.newHashSet();
		return vbaps;
	}

	public void setVbaps(Set<Vbap> vbaps) {
		this.vbaps = vbaps;
	}

	public Vbak() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vbak(String vbeln) {
		super();
		this.vbeln = vbeln;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getVbeln()).hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Vbak other = (Vbak) o;
		return new EqualsBuilder().append(getVbeln(), other.getVbeln())
				.isEquals();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getVbeln()).toString();
	}
}
