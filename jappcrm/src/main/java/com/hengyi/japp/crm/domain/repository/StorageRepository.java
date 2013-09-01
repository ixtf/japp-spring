package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.crm.domain.storage.Storage;

public interface StorageRepository extends GraphRepository<Storage>,
		NamedIndexRepository<Storage>,
		RelationshipOperationsRepository<Storage> {
}
