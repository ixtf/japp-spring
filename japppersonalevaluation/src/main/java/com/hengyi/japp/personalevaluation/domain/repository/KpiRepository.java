package com.hengyi.japp.personalevaluation.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.personalevaluation.domain.node.Kpi;

public interface KpiRepository extends GraphRepository<Kpi>,
		NamedIndexRepository<Kpi>, RelationshipOperationsRepository<Kpi> {
}
