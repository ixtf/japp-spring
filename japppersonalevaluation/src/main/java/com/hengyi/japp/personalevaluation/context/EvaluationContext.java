package com.hengyi.japp.personalevaluation.context;

import java.util.List;

import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;

public interface EvaluationContext {
	Task getTask();

	TaskConfig getTaskConfig();

	List<Person> getToEvaluatePersons();

	List<Person> getEvaluatedPersons();

	List<Person> getUnSubmitPersons();

	boolean canSubmit();

	void submit() throws Exception;
}
