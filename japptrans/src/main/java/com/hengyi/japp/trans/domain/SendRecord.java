package com.hengyi.japp.trans.domain;

import javax.persistence.MappedSuperclass;

import com.hengyi.japp.trans.domain.data.RecordType;

@MappedSuperclass
public abstract class SendRecord extends Record {
	private static final long serialVersionUID = -2460846344625881504L;

	@Override
	public RecordType getRecordType() {
		return RecordType.SEND;
	}
}
