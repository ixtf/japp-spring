package com.hengyi.japp.personalevaluation.context.impl;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.dozer.Mapper;
import org.primefaces.model.TreeNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.BiMap;
import com.google.common.eventbus.EventBus;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.context.ContextFactory;
import com.hengyi.japp.personalevaluation.context.EvaluationContext;
import com.hengyi.japp.personalevaluation.context.EvaluationPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonTreeNode;
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
			.newBuilder().expireAfterAccess((long) 0.5, TimeUnit.HOURS).build();
	private Cache<Long, EvaluationContext> evaluationContextCache = CacheBuilder
			.newBuilder().expireAfterAccess((long) 0.5, TimeUnit.HOURS).build();

	@Override
	public TaskConfigContext taskConfigContext(Long taskNodeId)
			throws Exception {
		taskService.checkCharger(taskNodeId);
		TaskConfigContext taskConfigContext = taskConfigContextCache
				.getIfPresent(taskNodeId);
		return taskConfigContext != null ? taskConfigContext
				: newTaskConfigContext(taskNodeId);
	}

	private TaskConfigContext newTaskConfigContext(Long taskNodeId)
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
		if (task == null || task.isInit())
			return new EvaluationContextImpl();
		else
			return new EvaluationContextImpl(
					taskConfigContext(task.getNodeId()), template, dozer,
					cacheService, operatorService, evaluationService);
	}

	@Override
	public EvaluationPersonContext evaluationPersonContext(Long personEndNodeId)
			throws Exception {
		Task task = cacheService.getCurrentTask();
		if (task == null)
			throw new Exception(Constant.ErrorCode.ERROR_NO_TASK);
		else if (task.isInit())
			throw new Exception(Constant.ErrorCode.ERROR_IS_INIT);
		else
			return new EvaluationPersonContextImpl(personEndNodeId, this,
					template, dozer, cacheService, operatorService,
					evaluationService);
	}
}
