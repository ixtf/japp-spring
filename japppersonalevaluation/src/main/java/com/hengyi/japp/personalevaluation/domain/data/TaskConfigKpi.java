package com.hengyi.japp.personalevaluation.domain.data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;

public class TaskConfigKpi implements ValueObject<TaskConfigKpi> {
	private static final long serialVersionUID = 5670190966225833499L;
	@NotBlank
	private String name;
	@NotNull
	@Min(0)
	private Double minScore;
	@NotNull
	@Min(5)
	private Double maxScore;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = StringUtils.trimToEmpty(name);
	}

	public Double getMinScore() {
		return minScore;
	}

	public void setMinScore(Double minScore) {
		this.minScore = minScore;
	}

	public Double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}

	@Override
	public boolean sameValueAs(final TaskConfigKpi other) {
		return other != null && Objects.equal(getName(), other.getName());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final TaskConfigKpi other = (TaskConfigKpi) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getName());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getName()).toString();
	}
}
