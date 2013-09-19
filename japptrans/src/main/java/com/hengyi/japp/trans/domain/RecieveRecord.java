package com.hengyi.japp.trans.domain;

import javax.persistence.MappedSuperclass;

import com.hengyi.japp.trans.domain.data.RecordType;

@MappedSuperclass
public abstract class RecieveRecord extends Record {
	private static final long serialVersionUID = -5901584389122508464L;

	@Override
	public RecordType getRecordType() {
		return RecordType.RECIEVE;
	}
}
