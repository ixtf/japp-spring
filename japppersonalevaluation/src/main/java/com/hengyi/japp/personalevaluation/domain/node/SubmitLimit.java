package com.hengyi.japp.personalevaluation.domain.node;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.Objects;
import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.personalevaluation.domain.data.CompareType;
import com.sun.istack.NotNull;

public class SubmitLimit implements ValueObject<SubmitLimit> {
	private static final long serialVersionUID = -5385149783088709799L;
	/*
	 * 提交限制中，一个可计算的配置段 如：3+5*5+（L1+L2)
	 */
	@NotBlank
	private String name;
	@NotBlank
	private String calaculate1;
	@NotBlank
	private String calaculate2;
	@NotNull
	private CompareType compare;
	private boolean submit;

	public boolean isSubmit() {
		return submit;
	}

	public void setSubmit(boolean submit) {
		this.submit = submit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCalaculate1() {
		return calaculate1;
	}

	public void setCalaculate1(String calaculate1) {
		this.calaculate1 = StringUtils.trimToEmpty(calaculate1).toUpperCase();
	}

	public String getCalaculate2() {
		return calaculate2;
	}

	public void setCalaculate2(String calaculate2) {
		this.calaculate2 = StringUtils.trimToEmpty(calaculate2).toUpperCase();
	}

	public CompareType getCompare() {
		return compare;
	}

	public void setCompare(CompareType compare) {
		this.compare = compare;
	}

	@Override
	public boolean sameValueAs(final SubmitLimit other) {
		return other != null
				&& Objects.equal(getCalaculate1(), other.getCalaculate1())
				&& Objects.equal(getCalaculate2(), other.getCalaculate2())
				&& Objects.equal(isSubmit(), other.isSubmit())
				&& Objects.equal(getCompare(), other.getCompare());

	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final SubmitLimit other = (SubmitLimit) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getCalaculate1(), getCalaculate2(),
				getCompare(), isSubmit());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getName()).toString();
	}
}
