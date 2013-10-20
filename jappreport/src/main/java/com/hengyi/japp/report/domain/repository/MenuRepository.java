package com.hengyi.japp.report.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.report.domain.Menu;

public interface MenuRepository extends GraphRepository<Menu>,
		NamedIndexRepository<Menu>, RelationshipOperationsRepository<Menu> {
}
