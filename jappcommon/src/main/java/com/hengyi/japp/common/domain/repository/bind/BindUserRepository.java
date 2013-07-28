package com.hengyi.japp.common.domain.repository.bind;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.common.domain.node.bind.BindUser;

public interface BindUserRepository extends GraphRepository<BindUser>,
		NamedIndexRepository<BindUser>,
		RelationshipOperationsRepository<BindUser> {
}
