package com.hengyi.japp.report.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Role;

public interface RoleRepository extends GraphRepository<Role>,
		NamedIndexRepository<Role>, RelationshipOperationsRepository<Role> {
	@Query(value = "START n=node({0}) MATCH n-[:" + Operator.OPERATOR_ROLE
			+ "]->role RETURN role")
	EndResult<Role> findAllByOperator(Operator operator);
}
