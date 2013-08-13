package com.hengyi.japp.crm.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.collect.Sets;
import com.hengyi.japp.crm.domain.node.Operator;

@Entity
@Table(name = "t_role")
public class Role implements Serializable {
	private static final long serialVersionUID = 2333427310871884384L;
	@NotBlank
	@Id
	@Column(length = 20, nullable = false)
	private String name;
	@NotBlank
	@Column(length = 50, nullable = false)
	private String displayName;
	@ManyToMany(mappedBy = "roles")
	private Set<Operator> operators;

	public String getName() {
		if (name != null)
			name = StringUtils.trim(name).toUpperCase();
		return name;
	}

	public Set<Operator> getOperators() {
		if (operators == null)
			operators = Sets.newHashSet();
		return operators;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).hashCode();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Role other = (Role) o;
		return new EqualsBuilder().append(getName(), other.getName())
				.isEquals();
	}
}
