package com.hengyi.japp.personalevaluation.context;

import java.util.List;

import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;

public interface EvaluationContext {
	TaskConfigContext getTaskConfigContext();

	TaskConfigPersonContext getTaskConfigPersonContext();

	Task getTask();

	TaskConfig getTaskConfig();

	Operator getOperator();

	Person getPerson();

	List<Person> getAllEvaluatePersons();

	List<Person> getToEvaluatePersons();

	List<LevelEvaluation> getEvaluatedPersons();

	List<Person> getUnSubmitPersons();

	boolean canSubmit();

	void submit() throws Exception;

	EvaluationPersonContext evaluationPerson(Person preson) throws Exception;
}
