package com.hengyi.japp.report.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.report.domain.Role;

public interface RoleRepository extends GraphRepository<Role>,
		NamedIndexRepository<Role>, RelationshipOperationsRepository<Role> {
}
