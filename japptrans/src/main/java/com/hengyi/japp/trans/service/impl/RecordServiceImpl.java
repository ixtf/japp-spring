package com.hengyi.japp.trans.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.trans.domain.Record;
import com.hengyi.japp.trans.domain.repository.RecordRepository;
import com.hengyi.japp.trans.service.RecordService;

@Named
@Transactional
public class RecordServiceImpl implements RecordService {
	@Inject
	private RecordRepository recordRepository;

	@Override
	public Record findOne(String uuid) {
		return uuid == null ? null : recordRepository.findOne(uuid);
	}

	@Override
	public void save(Record record) {
		recordRepository.save(record);
	}

	@Override
	public void delete(Record record) {
		recordRepository.delete(record);
	}
}
