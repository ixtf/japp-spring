package com.hengyi.japp.report.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.report.domain.Operator;

public interface OperatorRepository extends GraphRepository<Operator>,
		NamedIndexRepository<Operator>,
		RelationshipOperationsRepository<Operator> {
}
