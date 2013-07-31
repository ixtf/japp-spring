package com.hengyi.japp.personalevaluation.context.impl;

import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.primefaces.model.TreeNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.context.EvaluationContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;
import com.hengyi.japp.personalevaluation.service.CacheServiceFacade;
import com.hengyi.japp.personalevaluation.service.EvaluationService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.utils.MyUtil;

public class EvaluationContextImpl implements EvaluationContext {
	private TaskConfigContext taskConfigContext;
	private TaskConfigPersonContext taskConfigPersonContext;
	private Neo4jOperations template;
	private EvaluationService evaluationService;

	private Task task;
	private TaskConfig taskConfig;
	private Operator operator;
	private Person person;
	private List<Person> toEvaluatePersons;
	private List<Person> evaluatedPersons;
	private List<Person> unSubmitPersons;
	private TreeNode taskPersonTreeNode;

	public EvaluationContextImpl(TaskConfigContext taskConfigContext,
			Neo4jOperations template, Mapper dozer, CacheServiceFacade cacheService,
			OperatorService operatorService, EvaluationService evaluationService)
			throws Exception {
		this.taskConfigContext = taskConfigContext;
		this.template = template;
		this.evaluationService = evaluationService;
		operator = cacheService.getCurrentOperator();
		taskConfigPersonContext = taskConfigContext
				.taskConfigPersonContext(operator);
		taskPersonTreeNode = taskConfigPersonContext.getTaskPersonTreeNode();
		task = taskConfigContext.getTask();
		taskConfig = taskConfigContext.getTaskConfig();
		person = evaluationService.findOnePerson(task, operator);

		initEvaluatedPersons();
		initUnSubmitPersons();
		initToEvaluatePersons();
	}

	private void initEvaluatedPersons() {
		for (LevelEvaluation levelEvaluation : person
				.getLevelEvaluations(template)) {
			Person person = levelEvaluation.getPersonEnd(template);
			getEvaluatedPersons().add(person);
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

	private void initToEvaluatePersons() {
		if (person.isEvaluable())
			getToEvaluatePersons().add(person);
		addTo(getToEvaluatePersons(),
				MyUtil.getAllSubTreeNode(taskPersonTreeNode));
		getToEvaluatePersons().removeAll(getEvaluatedPersons());
		getToEvaluatePersons().removeAll(getUnSubmitPersons());
	}

	private void addTo(List<Person> list, Set<TreeNode> nodes) {
		for (TreeNode node : nodes) {
			Person person = evaluationService.findOnePerson(task,
					taskConfigContext.getOperator(node));
			if (person.isEvaluable())
				list.add(person);
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
	public List<Person> getToEvaluatePersons() {
		if (toEvaluatePersons == null)
			toEvaluatePersons = Lists.newArrayList();
		return toEvaluatePersons;
	}

	@Override
	public List<Person> getEvaluatedPersons() {
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
	public void submit() throws Exception {
		if (canSubmit())
			evaluationService.submit();
		else
			throw new Exception(Constant.ErrorCode.ERROR_NOT_SUBMIT);
	}

	@Override
	public boolean canSubmit() {
		return getToEvaluatePersons().isEmpty()
				&& getUnSubmitPersons().isEmpty();
	}

	public EvaluationContextImpl() {
		super();
	}
}
