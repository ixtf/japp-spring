package com.hengyi.japp.crm.domain;

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
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.Constant;

@Entity
@Table(name = "t_operator")
public class Operator implements Serializable {
	private static final long serialVersionUID = -4364743082478493931L;
	@NotBlank
	@Id
	private String uuid;
	@NotBlank
	@Size(max = 20)
	@Column(length = 20)
	private String name;
	@Column(length = 20)
	private String theme;
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

	public String getTheme() {
		if (theme == null)
			theme = Constant.DEFAULT_THEME.getName();
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
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
