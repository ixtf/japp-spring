package com.hengyi.japp.trans.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hengyi.japp.trans.domain.Record;

public interface RecordRepository extends JpaRepository<Record, String> {
}
