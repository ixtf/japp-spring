package com.hengyi.japp.foreign.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Sets;

@Entity
@Table(name = "t_operator")
public class Operator implements Serializable {
	private static final long serialVersionUID = -4364743082478493931L;
	@NotBlank
	@Id
	private String uuid;
	@NotBlank
	@Column(length = 20)
	private String name;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "t_operator_role")
	private Set<Role> roles;

	public Set<Role> getRoles() {
		if (roles == null)
			roles = Sets.newHashSet();
		return roles;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Operator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operator(String uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getUuid()).hashCode();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Operator other = (Operator) o;
		return new EqualsBuilder().append(getUuid(), other.getUuid())
				.isEquals();
	}
}
