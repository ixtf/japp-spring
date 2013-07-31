package com.hengyi.japp.personalevaluation.service;

import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;

public interface TaskService {
	Task findOne(Long nodeId);

	Iterable<Task> findAllByCharger(int year) throws Exception;

	Iterable<Task> findAllByOperator(int year) throws Exception;

	void saveTask(Task task) throws Exception;

	void configTask(Task task, TaskConfig taskConfig,
			Iterable<Operator> chargers) throws Exception;

	void activeTask(Long nodeId) throws Exception;

	void finishTask(Long nodeId) throws Exception;

	void deleteTask(Long nodeId) throws Exception;

	void checkCharger(Long taskNodeId) throws Exception;

	Task getDefaultTask(Operator operator);
}
