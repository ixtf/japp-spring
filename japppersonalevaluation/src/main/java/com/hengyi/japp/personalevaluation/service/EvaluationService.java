package com.hengyi.japp.personalevaluation.service;

import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;

@Transactional
public interface EvaluationService {
	Person findOnePerson(Long nodeId) throws Exception;

	Person findOnePerson(Task task, Operator operator);

	Iterable<Person> findAllPerson(Task task, Iterable<Operator> operators);

	void evaluate(Person personStart, Person personEnd,
			LevelEvaluation levelEvaluation,
			Iterable<KpiEvaluation> kpiEvaluations) throws Exception;

	void checkEvaluation(Long personNodeId) throws Exception;

	void submit() throws Exception;
}
