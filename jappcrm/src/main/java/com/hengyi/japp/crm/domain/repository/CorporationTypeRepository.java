package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.crm.domain.CorporationType;

public interface CorporationTypeRepository extends
		GraphRepository<CorporationType>,
		NamedIndexRepository<CorporationType>,
		RelationshipOperationsRepository<CorporationType> {
}
