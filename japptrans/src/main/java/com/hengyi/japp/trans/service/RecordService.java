package com.hengyi.japp.trans.service;

import com.hengyi.japp.trans.domain.Record;

public interface RecordService {
	Record findOne(String uuid);

	void save(Record record);

	void delete(Record record);
}
