package com.hengyi.japp.crm.domain;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
public class Noteable extends Modifiable {
	private static final long serialVersionUID = -3894049575627384096L;
	protected String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
