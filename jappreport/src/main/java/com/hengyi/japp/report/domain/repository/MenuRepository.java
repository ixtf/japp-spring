package com.hengyi.japp.report.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Role;

public interface MenuRepository extends GraphRepository<Menu>,
		NamedIndexRepository<Menu>, RelationshipOperationsRepository<Menu> {
	@Query(value = "START n=node({0}) MATCH n-[:" + Role.ROLE_MENU
			+ "]->menu RETURN menu")
	EndResult<Menu> findAllByRole(Iterable<Role> roles);
}
