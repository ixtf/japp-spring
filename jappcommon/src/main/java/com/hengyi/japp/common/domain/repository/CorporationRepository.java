package com.hengyi.japp.common.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.common.domain.node.Corporation;

public interface CorporationRepository extends GraphRepository<Corporation>,
		NamedIndexRepository<Corporation>,
		RelationshipOperationsRepository<Corporation> {
}
