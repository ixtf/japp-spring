package com.hengyi.japp.personalevaluation.context.impl;

import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.personalevaluation.context.ContextFactory;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigKpi;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.service.CacheService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.service.TaskService;

public abstract class AbstractTaskConfigContext implements TaskConfigContext {
	protected ContextFactory contextFactory;
	protected Neo4jOperations template;
	protected Mapper dozer;
	protected CacheService cacheService;
	protected OperatorService operatorService;
	protected TaskService taskService;

	protected final Task task;
	protected final TaskConfig taskConfig;

	private Set<Operator> chargers;
	private Set<TaskConfigKpi> taskKpis;

	@Override
	public final List<Operator> getChargers() {
		return Lists.newArrayList(chargers);
	}

	@Override
	public final void addCharger(Operator charger) {
		chargers.add(charger);
	}

	@Override
	public final void removeCharger(Operator charger) {
		chargers.remove(charger);
	}

	@Override
	public final List<TaskConfigKpi> getTaskKpis() {
		return Lists.newArrayList(taskKpis);
	}

	@Override
	public final void addTaskKpi(TaskConfigKpi kpi) {
		taskKpis.add(kpi);
	}

	@Override
	public final void removeTaskKpi(TaskConfigKpi kpi) {
		taskKpis.remove(kpi);
	}

	public AbstractTaskConfigContext(Long taskNodeId,
			ContextFactory contextFactory, Neo4jOperations template,
			Mapper dozer, CacheService cacheService,
			OperatorService operatorService, TaskService taskService)
			throws Exception {
		super();
		this.contextFactory = contextFactory;
		this.template = template;
		this.dozer = dozer;
		this.cacheService = cacheService;
		this.operatorService = operatorService;
		this.taskService = taskService;
		task = taskService.findOne(taskNodeId);
		taskConfig = task.getConfig(template);
		chargers = Sets.newHashSet(task.getChargers(template));
		taskKpis = Sets.newHashSet(taskConfig.getTaskKpis());
	}

	@Override
	public final void save() throws Exception {
		updateConfig();
		taskConfig.setTaskKpis(taskKpis);
		taskService.configTask(task, taskConfig, chargers);
	}

	@Override
	public final Task getTask() {
		return task;
	}

	@Override
	public final TaskConfig getTaskConfig() {
		return taskConfig;
	}

	abstract void updateConfig() throws Exception;
}
