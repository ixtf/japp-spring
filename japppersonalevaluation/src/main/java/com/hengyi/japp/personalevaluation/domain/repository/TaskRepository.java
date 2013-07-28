package com.hengyi.japp.personalevaluation.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.Operator;

public interface TaskRepository extends GraphRepository<Task>,
		NamedIndexRepository<Task>, RelationshipOperationsRepository<Task> {
	// @Query("start user=node({0}) match user-[:AS]->person<-[:MANAGE*0..]-root<-[:TASK_PERSON]-task return task")
	@Query("start operator=node({0}) match operator-[:"
			+ Operator.OPERATOR_PERSON
			+ "]->person<-[:TASK_PERSON]-task return task")
	EndResult<Task> findAllByOperator(Operator operator);

	// @Query("start user=node({0}) match user-[:AS]->person<-[:MANAGE*0..]-root<-[:TASK_PERSON]-task where task.year={1} return task")
	@Query("start operator=node({0}) match operator-[:"
			+ Operator.OPERATOR_PERSON + "]->person<-[:" + Task.TASK_PERSON
			+ "]-task where task.year={1} return task")
	EndResult<Task> findAllByOperator(Operator operator, int year);

	@Query("start operator=node({0}) match operator-[:"
			+ Operator.OPERATOR_TASK + "]->task return task")
	EndResult<Task> findAllByCharger(Operator operator);

	@Query("start operator=node({0}) match operator-[:"
			+ Operator.OPERATOR_TASK
			+ "]->task where task.year={1} return task")
	EndResult<Task> findAllByCharger(Operator operator, int year);
}
