package com.hengyi.japp.common.domain.repository.bind;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.common.domain.node.bind.user.InnerUser;

public interface InnerUserRepository extends GraphRepository<InnerUser>,
		NamedIndexRepository<InnerUser>,
		RelationshipOperationsRepository<InnerUser> {
}
