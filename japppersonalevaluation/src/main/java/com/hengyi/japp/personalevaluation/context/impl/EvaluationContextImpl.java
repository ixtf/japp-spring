package com.hengyi.japp.personalevaluation.context.impl;

import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.primefaces.model.TreeNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.Constant.ErrorCode;
import com.hengyi.japp.personalevaluation.context.ContextFactory;
import com.hengyi.japp.personalevaluation.context.EvaluationContext;
import com.hengyi.japp.personalevaluation.context.EvaluationPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;
import com.hengyi.japp.personalevaluation.event.EvaluationEvent;
import com.hengyi.japp.personalevaluation.service.CacheServiceFacade;
import com.hengyi.japp.personalevaluation.service.EvaluationService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.utils.MyUtil;

public class EvaluationContextImpl implements EvaluationContext {
	private final TaskConfigContext taskConfigContext;
	private final TaskConfigPersonContext taskConfigPersonContext;
	private final Task task;
	private final TaskConfig taskConfig;
	private final ContextFactory contextFactory;
	private final Neo4jOperations template;
	private final EvaluationService evaluationService;

	private Operator operator;
	private Person person;
	private List<Person> allEvaluatePersons;
	private List<Person> toEvaluatePersons;
	private List<LevelEvaluation> evaluatedPersons;
	private List<Person> unSubmitPersons;
	private TreeNode taskPersonTreeNode;

	public EvaluationContextImpl(
			TaskConfigPersonContext taskConfigPersonContext,
			ContextFactory contextFactory, Neo4jOperations template,
			Mapper dozer, EventBus eventBus, CacheServiceFacade cacheService,
			OperatorService operatorService, EvaluationService evaluationService)
			throws Exception {
		this.taskConfigPersonContext = taskConfigPersonContext;
		this.taskConfigContext = taskConfigPersonContext.getTaskConfigContext();
		this.contextFactory = contextFactory;
		this.template = template;
		this.evaluationService = evaluationService;
		operator = taskConfigPersonContext.getOperator();
		taskPersonTreeNode = taskConfigPersonContext.getTaskPersonTreeNode();
		task = taskConfigContext.getTask();
		taskConfig = taskConfigContext.getTaskConfig();
		person = evaluationService.findOnePerson(task, operator);

		initToEvaluatePersons();
		initEvaluatedPersons();
		initUnSubmitPersons();
		for (LevelEvaluation levelEvaluation : getEvaluatedPersons())
			getToEvaluatePersons().remove(levelEvaluation.getPersonEnd());
		getToEvaluatePersons().removeAll(getUnSubmitPersons());
	}

	private void initToEvaluatePersons() {
		if (person.isEvaluable())
			getToEvaluatePersons().add(person);
		addTo(getToEvaluatePersons(),
				MyUtil.getAllSubTreeNode(taskPersonTreeNode));
	}

	private void addTo(List<Person> list, Set<TreeNode> nodes) {
		for (TreeNode node : nodes) {
			Person person = evaluationService.findOnePerson(task,
					taskConfigContext.getOperator(node));
			if (person.isEvaluable())
				list.add(person);
		}
	}

	private void initEvaluatedPersons() {
		for (LevelEvaluation levelEvaluation : person.getEvaluations(template)) {
			Person person = levelEvaluation.getPersonEnd(template);
			getEvaluatedPersons().add(levelEvaluation);
		}
	}

	private void initUnSubmitPersons() {
		if (taskPersonTreeNode.isLeaf())
			return;
		for (TreeNode node : taskPersonTreeNode.getChildren()) {
			Person person = evaluationService.findOnePerson(task,
					taskConfigContext.getOperator(node));
			if (!person.isSubmit()) {
				if (person.isEvaluable())
					getUnSubmitPersons().add(person);
				addTo(getUnSubmitPersons(), MyUtil.getAllSubTreeNode(node));
			}
		}
	}

	@Override
	public Task getTask() {
		return task;
	}

	@Override
	public TaskConfig getTaskConfig() {
		return taskConfig;
	}

	@Override
	public Operator getOperator() {
		return operator;
	}

	@Override
	public Person getPerson() {
		return person;
	}

	@Override
	public List<Person> getToEvaluatePersons() {
		if (toEvaluatePersons == null)
			toEvaluatePersons = Lists.newArrayList();
		return toEvaluatePersons;
	}

	@Override
	public List<LevelEvaluation> getEvaluatedPersons() {
		if (evaluatedPersons == null)
			evaluatedPersons = Lists.newArrayList();
		return evaluatedPersons;
	}

	@Override
	public List<Person> getUnSubmitPersons() {
		if (unSubmitPersons == null)
			unSubmitPersons = Lists.newArrayList();
		return unSubmitPersons;
	}

	@Override
	public List<Person> getAllEvaluatePersons() {
		if (allEvaluatePersons == null) {
			allEvaluatePersons = Lists.newArrayList();
			allEvaluatePersons.addAll(getToEvaluatePersons());
			for (LevelEvaluation levelEvaluation : getEvaluatedPersons())
				allEvaluatePersons.add(levelEvaluation.getPersonEnd());
			allEvaluatePersons.addAll(getUnSubmitPersons());
		}
		return allEvaluatePersons;
	}

	@Override
	public void submit() throws Exception {
		if (canSubmit())
			evaluationService.submit();
		else
			throw new Exception(Constant.ErrorCode.ERROR_NOT_SUBMIT);
	}

	@Override
	public boolean canSubmit() {
		return getToEvaluatePersons().isEmpty()
				&& getUnSubmitPersons().isEmpty()
				&& (!getEvaluatedPersons().isEmpty());
	}

	@Override
	public EvaluationPersonContext evaluationPerson(Person person)
			throws Exception {
		if (getAllEvaluatePersons().contains(person))
			return contextFactory.evaluationPersonContext(this, person);
		throw new Exception(ErrorCode.SYSTEM);
	}

	@Override
	public TaskConfigContext getTaskConfigContext() {
		return taskConfigContext;
	}

	@Override
	public TaskConfigPersonContext getTaskConfigPersonContext() {
		return taskConfigPersonContext;
	}
}
