package com.hengyi.japp.personalevaluation.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;

public interface PersonRepository extends GraphRepository<Person>,
		NamedIndexRepository<Person>, RelationshipOperationsRepository<Person> {
	// @Query("start task=node({0}) match task-[:TASK_PERSON]->root-[:MANAGE*0..]->person return person")
	// EndResult<Person> findAllByTask(Task task);

	@Query("start task=node({0}),operator=node({1}) match task-[:"
			+ Task.TASK_PERSON + "]->person<-[:" + Operator.OPERATOR_PERSON
			+ "]-operator return person")
	Person findOne(Task task, Operator operator);

	@Query("start task=node({0}),operator=node({1}) match task-[:"
			+ Task.TASK_PERSON + "]->person<-[:" + Operator.OPERATOR_PERSON
			+ "]-operator return person")
	EndResult<Person> findAll(Task task, Iterable<Operator> operators);

	// @Query("start person=node({0}) match person-[:MANAGE*0..]->sub return sub")
	// EndResult<Person> findAllSubPerson(Person person);

	// @Query("start manager=node({0}),person=node({1}) match manager-[:MANAGE*0..]->person return manager")
	// Person findAllManageRel(Person manager, Person person);

	// @Query("start manager=node({0}),person=node({1}) "
	// +
	// "match manager-[:MANAGE]->submanager-[:MANAGE*0..]->person return submanager")
	// Person findDirectSubManager(Person manager, Person person);
}
