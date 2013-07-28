package com.hengyi.japp.common.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.common.domain.node.User;

public interface UserRepository extends GraphRepository<User>,
		NamedIndexRepository<User>, RelationshipOperationsRepository<User> {
}
