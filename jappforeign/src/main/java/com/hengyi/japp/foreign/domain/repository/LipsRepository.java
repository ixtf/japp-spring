package com.hengyi.japp.foreign.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hengyi.japp.common.sap.dto.LipsPK;
import com.hengyi.japp.foreign.domain.Lips;


public interface LipsRepository extends JpaRepository<Lips, LipsPK>{
}
