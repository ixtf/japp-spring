package com.hengyi.japp.crm.web.data;

import java.io.Serializable;

import com.google.common.base.Objects;
import com.hengyi.japp.crm.domain.Crm;

public class AssociateModel implements Serializable {
	private static final long serialVersionUID = -1262051757991564211L;
	private Crm crm;
	private Crm associateCrm;
	private String note;

	public AssociateModel() {
		super();
	}

	public AssociateModel(Crm crm) {
		super();
		this.crm = crm;
	}

	public Crm getCrm() {
		return crm;
	}

	public void setCrm(Crm crm) {
		this.crm = crm;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Crm getAssociateCrm() {
		return associateCrm;
	}

	public void setAssociateCrm(Crm associateCrm) {
		this.associateCrm = associateCrm;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AssociateModel other = (AssociateModel) o;
		return Objects.equal(getCrm(), other.getCrm())
				&& Objects.equal(getAssociateCrm(), other.getAssociateCrm());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getCrm(), getAssociateCrm());
	}

	@Override
	public String toString() {
		if (getCrm() == null)
			return null;
		return getCrm().toString();
	}
}