package com.hengyi.japp.personalevaluation.service.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.dozer.Mapper;
import org.neo4j.graphdb.Relationship;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.Constant.ErrorCode;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigKpi;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigPerson;
import com.hengyi.japp.personalevaluation.domain.data.TaskStatus;
import com.hengyi.japp.personalevaluation.domain.node.Kpi;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.domain.repository.KpiRepository;
import com.hengyi.japp.personalevaluation.domain.repository.PersonRepository;
import com.hengyi.japp.personalevaluation.domain.repository.TaskConfigRepository;
import com.hengyi.japp.personalevaluation.domain.repository.TaskRepository;
import com.hengyi.japp.personalevaluation.service.CacheService;
import com.hengyi.japp.personalevaluation.service.EvaluationService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.service.TaskService;

@Named
@Transactional
// @Veto
public class TaskServiceImpl implements TaskService {
	@Inject
	private CacheService cacheService;
	@Inject
	private Neo4jOperations template;
	@Inject
	private OperatorService operatorService;
	@Inject
	private EvaluationService personService;
	@Inject
	private TaskRepository taskRepository;
	@Inject
	private TaskConfigRepository taskConfigRepository;
	@Inject
	private PersonRepository personRepository;
	@Inject
	private KpiRepository kpiRepository;
	@Inject
	private Mapper dozer;

	@Override
	public Task findOne(Long nodeId) {
		return taskRepository.findOne(nodeId);
	}

	@Override
	public void configTask(Task task, TaskConfig taskConfig,
			Iterable<Operator> chargers) throws Exception {
		checkConfig(task.getNodeId());
		task.setChargers(chargers);
		task.setConfig(taskConfig);
		taskConfigRepository.save(taskConfig);
		taskRepository.save(task);
	}

	private void checkConfig(Long nodeId) throws Exception {
		Task task = findOne(nodeId);
		if (!task.isInit())
			throw new Exception(ErrorCode.ERROR_TASK_NOT_INIT);
		checkCharger(nodeId);
	}

	@Override
	public void saveTask(final Task taskData) throws Exception {
		if (!SecurityUtils.getSubject().hasRole("admin"))
			throw new Exception(ErrorCode.ERROR_NOT_ADMIN);
		Long taskNodeId = taskData.getNodeId();
		if (taskNodeId == null) {
			taskRepository.save(taskData);
			return;
		}
		Task task = findOne(taskNodeId);
		if (!task.isInit())
			throw new Exception(ErrorCode.ERROR_TASK_NOT_INIT);
		taskRepository.save(taskData);
	}

	@Override
	public void activeTask(Long taskNodeId) throws Exception {
		checkActive(taskNodeId);
		generatePersons(taskNodeId);
		Task task = findOne(taskNodeId);
		task.setStatus(TaskStatus.ACTIVE);
		taskRepository.save(task);
	}

	private void checkActive(Long nodeId) throws Exception {
		Task task = findOne(nodeId);
		if (!task.isInit())
			throw new Exception(ErrorCode.ERROR_NOT_INIT);
		checkCharger(nodeId);
		TaskConfig config = task.getConfig(template);
		if (config.getNodeId() == null)
			throw new Exception(ErrorCode.ERROR_NO_TASK_CONFIG);
		Iterable<TaskConfigKpi> kpis = config.getTaskKpis();
		if (!kpis.iterator().hasNext())
			throw new Exception(ErrorCode.ERROR_NO_TASK_KPIS);
		Iterable<TaskConfigPerson> persons = config.getTaskPersons();
		if (!persons.iterator().hasNext())
			throw new Exception(ErrorCode.ERROR_NO_TASK_PERSONS);
	}

	private void generatePersons(Long taskNodeId) throws Exception {
		Task task = findOne(taskNodeId);
		TaskConfig taskConfig = task.getConfig(template);
		Iterable<TaskConfigKpi> taskKpis = taskConfig.getTaskKpis();
		for (TaskConfigPerson taskPerson : taskConfig.getTaskPersons()) {
			Person person = new Person();
			person.setTask(task);
			person.setOperator(operatorService.findOne(taskPerson.getNodeId()));
			person.setStatistic(taskPerson.isStatistic());
			person.setEvaluable(taskPerson.isEvaluable());
			person.setKpis(generateKpis(taskPerson.getKpis(), taskKpis));
			personRepository.save(person);
		}
	}

	private Iterable<Kpi> generateKpis(Iterable<TaskConfigKpi> personKpis,
			Iterable<TaskConfigKpi> taskKpis) {
		Set<Kpi> result = Sets.newHashSet();
		Iterable<TaskConfigKpi> kpis = personKpis.iterator().hasNext() ? personKpis
				: taskKpis;
		for (TaskConfigKpi kpi : kpis)
			result.add(kpiRepository.save(dozer.map(kpi, Kpi.class)));
		return result;
	}

	@Override
	public void finishTask(Long taskNodeId) throws Exception {
		checkFinish(taskNodeId);
		generateFinalEvaluation(taskNodeId);
		Task task = findOne(taskNodeId);
		task.setStatus(TaskStatus.FINISH);
		taskRepository.save(task);
	}

	private void checkFinish(Long taskNodeId) throws Exception {
		Task task = findOne(taskNodeId);
		if (!task.isActive())
			throw new Exception(ErrorCode.ERROR_NOT_ACTIVE);
		checkCharger(taskNodeId);
		Iterable<Person> persons = task.getPersons();
		if (!persons.iterator().hasNext())
			throw new Exception(ErrorCode.ERROR_NOT_PERSON);
		for (Person person : persons) {
			if (!person.isSubmit())
				throw new Exception(ErrorCode.ERROR_NOT_SUBMIT);
		}
	}

	private void generateFinalEvaluation(Long taskNodeId) {
	}

	@Override
	public void deleteTask(Long nodeId) throws Exception {
		checkDelete(nodeId);
		Task task = findOne(nodeId);
		taskConfigRepository.delete(task.getConfig(template));
		for (Person person : task.getPersons(template)) {
			kpiRepository.delete(person.getKpis(template));
			personRepository.delete(person);
		}
		taskRepository.delete(task);
	}

	private void checkDelete(Long nodeId) throws Exception {
		Task task = findOne(nodeId);
		if (task.isFinish())
			throw new Exception(ErrorCode.ERROR_TASK_FINISHED);
		checkCharger(nodeId);
	}

	@Override
	public void checkCharger(Long nodeId) throws Exception {
		if (SecurityUtils.getSubject().hasRole("admin"))
			return;
		Task task = findOne(nodeId);
		Operator operator = cacheService.getCurrentOperator();
		Relationship r = template.getRelationshipBetween(operator, task,
				Operator.OPERATOR_TASK);
		if (r == null)
			throw new Exception(ErrorCode.ERROR_NOT_CHARGER);
	}

	// @Override
	// public Iterable<Task> findAll() throws Exception {
	// return SecurityUtils.getSubject().hasRole("admin") ? Sets
	// .newHashSet(taskRepository.findAll()) : Sets
	// .newHashSet(taskRepository.findAllByCharger(cacheService
	// .getCurrentOperator()));
	// }

	@Override
	public Iterable<Task> findAllByCharger(int year) throws Exception {
		if (year < Constant._YEAR)
			return Sets.newHashSet();
		return SecurityUtils.getSubject().hasRole("admin") ? Sets
				.newHashSet(taskRepository.findAllByPropertyValue("year", year))
				: Sets.newHashSet(taskRepository.findAllByCharger(
						cacheService.getCurrentOperator(), year));
	}

	@Override
	public Iterable<Task> findAllByOperator(int year) throws Exception {
		if (year < Constant._YEAR)
			return Sets.newHashSet();
		return SecurityUtils.getSubject().hasRole("admin") ? Sets
				.newHashSet(taskRepository.findAllByPropertyValue("year", year))
				: Sets.newHashSet(taskRepository.findAllByOperator(
						cacheService.getCurrentOperator(), year));
	}

	@Override
	public Task getDefaultTask(Operator operator) {
		for (Task task : taskRepository.findAllByOperator(operator))
			if (TaskStatus.ACTIVE.equals(task.getStatus()))
				return task;
		return null;
	}

	@Override
	public void checkEvaluation(Long taskNodeId) throws Exception {
		Task task = findOne(taskNodeId);
		if (!task.isActive())
			throw new Exception(ErrorCode.ERROR_NOT_ACTIVE);
	}
}
