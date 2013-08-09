package com.hengyi.japp.personalevaluation.context.impl;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

import org.dozer.Mapper;
import org.primefaces.model.TreeNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.BiMap;
import com.google.common.eventbus.EventBus;
import com.hengyi.japp.personalevaluation.context.ContextFactory;
import com.hengyi.japp.personalevaluation.context.EvaluationContext;
import com.hengyi.japp.personalevaluation.context.EvaluationPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonTreeNode;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.service.CacheServiceFacade;
import com.hengyi.japp.personalevaluation.service.EvaluationService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.service.TaskService;

@Named
@Singleton
public class ContextFactoryImpl implements ContextFactory {
	@Inject
	private Neo4jOperations template;
	@Inject
	private CacheServiceFacade cacheService;
	@Inject
	private Mapper dozer;
	@Inject
	private OperatorService operatorService;
	@Inject
	private TaskService taskService;
	@Inject
	private EvaluationService evaluationService;
	@Inject
	private EventBus eventBus;

	private Cache<Long, TaskConfigContext> taskConfigContextCache = CacheBuilder
			.newBuilder().maximumSize(5)
			.expireAfterAccess(10, TimeUnit.MINUTES).build();

	@Override
	public TaskConfigContext taskConfigContext() throws Exception {
		Task task = cacheService.getCurrentTask();
		if (task == null)
			throw new Exception();
		return taskConfigContext(task.getNodeId());
	}

	@Override
	public TaskConfigContext taskConfigContext(@NotNull Long taskNodeId)
			throws Exception {
		TaskConfigContext taskConfigContext = taskConfigContextCache
				.getIfPresent(taskNodeId);
		return taskConfigContext != null ? taskConfigContext
				: newTaskConfigContext(taskNodeId);
	}

	private TaskConfigContext newTaskConfigContext(@NotNull Long taskNodeId)
			throws Exception {
		TaskConfigContext taskConfigContext = new TaskConfigContextImpl(
				taskNodeId, this, template, dozer, cacheService,
				operatorService, taskService);
		if (!taskConfigContext.getTask().isInit())
			taskConfigContextCache.put(taskNodeId, taskConfigContext);
		return taskConfigContext;
	}

	@Override
	public TaskConfigPersonContext taskConfigPersonContext(
			TaskConfigContext taskConfigContext,
			BiMap<TaskConfigPersonTreeNode, TreeNode> treeNodeMap,
			TreeNode taskPersonTreeNode) throws Exception {
		return new TaskConfigPersonContextImpl(taskConfigContext, treeNodeMap,
				taskPersonTreeNode);
	}

	@Override
	public EvaluationContext evaluationContext() throws Exception {
		Task task = cacheService.getCurrentTask();
		Operator operator = cacheService.getCurrentOperator();
		if (task == null || operator == null)
			throw new Exception();
		return evaluationContext(task.getNodeId(), operator.getNodeId());
	}

	@Override
	public EvaluationContext evaluationContext(@NotNull Long taskNodeId,
			@NotNull Long operatorNodeId) throws Exception {
		taskService.checkEvaluation(taskNodeId);
		TaskConfigContext taskConfigContext = taskConfigContext(taskNodeId);
		Operator operator = operatorService.findOne(operatorNodeId);
		return new EvaluationContextImpl(
				taskConfigContext.taskConfigPersonContext(operator), this,
				template, dozer, eventBus, cacheService, operatorService,
				evaluationService);
	}

	@Override
	public EvaluationPersonContext evaluationPersonContext(
			EvaluationContext evaluationContext, Person personEnd)
			throws Exception {
		return new EvaluationPersonContextImpl(personEnd, evaluationContext,
				template, dozer, eventBus, cacheService, operatorService,
				evaluationService);
	}
}
