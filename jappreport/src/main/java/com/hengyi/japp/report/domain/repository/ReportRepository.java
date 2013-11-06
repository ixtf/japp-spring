package com.hengyi.japp.report.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.Role;

public interface ReportRepository extends GraphRepository<Report>,
		NamedIndexRepository<Report>, RelationshipOperationsRepository<Report> {
	@Query(value = "START n=node({0}) MATCH n-[:" + Menu.MENU_REPORT
			+ "]->report RETURN report")
	EndResult<Report> findAllByMenu(Menu menu);

	@Query(value = "START n=node({0}) MATCH n-[:" + Menu.MENU_REPORT
			+ "]->report RETURN report")
	EndResult<Report> findAllByMenu(Iterable<Menu> menus);

	@Query(value = "START n=node({0}) MATCH n-[:" + Role.ROLE_REPORT
			+ "]->report RETURN report")
	EndResult<Report> findAllByRole(Role role);

	@Query(value = "START n=node({0}) MATCH n-[:" + Role.ROLE_REPORT
			+ "]->report RETURN report")
	EndResult<Report> findAllByRole(Iterable<Role> roles);
}
