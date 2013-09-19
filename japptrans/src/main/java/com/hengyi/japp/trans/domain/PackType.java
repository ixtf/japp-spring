package com.hengyi.japp.trans.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.Field;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;

@MappedSuperclass
public abstract class PackType implements Serializable {
	private static final long serialVersionUID = 1875276294577490620L;
	@Id
	private Integer id;
	@Field
	@NotBlank
	@Column(length = 20)
	private String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final PackType other = (PackType) o;
		return Objects.equal(getId(), other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return getName();
	}
}
