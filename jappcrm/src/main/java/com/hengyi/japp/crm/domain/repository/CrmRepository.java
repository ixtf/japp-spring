package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.crm.domain.Crm;

public interface CrmRepository extends GraphRepository<Crm>,
		NamedIndexRepository<Crm>, RelationshipOperationsRepository<Crm> {
}
