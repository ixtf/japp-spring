package com.hengyi.japp.personalevaluation.context.impl;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.dozer.Mapper;
import org.primefaces.model.TreeNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.hengyi.japp.personalevaluation.context.ContextFactory;
import com.hengyi.japp.personalevaluation.context.EvaluationPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.domain.node.Kpi;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;
import com.hengyi.japp.personalevaluation.service.CacheServiceFacade;
import com.hengyi.japp.personalevaluation.service.EvaluationService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.utils.MyUtil;

public class EvaluationPersonContextImpl implements EvaluationPersonContext {
	private TaskConfigContext taskConfigContext;
	private TaskConfigPersonContext personConfigContext;
	private Neo4jOperations template;
	private EvaluationService evaluationService;

	private final Task task;
	private final TaskConfig taskConfig;
	private Operator operator;
	private Person personStart;
	private Person personEnd;
	// personEnd的评价数据提交顺序
	private List<Person> evaluateList;
	// private List<Long> evaluateList;
	private List<LevelEvaluation> levelEvaluations;
	private Multimap<Kpi, KpiEvaluation> kpiEvaluationMap;

	public EvaluationPersonContextImpl(@NotNull Long personEndNodeId,
			ContextFactory contextFactory, Neo4jOperations template,
			Mapper dozer, CacheServiceFacade cacheService,
			OperatorService operatorService, EvaluationService evaluationService)
			throws Exception {
		this.template = template;
		this.evaluationService = evaluationService;
		operator = cacheService.getCurrentOperator();
		task = cacheService.getCurrentTask();
		personStart = evaluationService.findOnePerson(task, operator);
		personEnd = evaluationService.findOnePerson(personEndNodeId);
		taskConfigContext = contextFactory.taskConfigContext(task.getNodeId());
		personConfigContext = taskConfigContext
				.taskConfigPersonContext(operator);
		taskConfig = taskConfigContext.getTaskConfig();

		// evaluateList = MyUtil.getEvaluateList(taskConfig, personEnd);
		initEvaluationList();
		initLevelEvaluations();
		initKpiEvaluationMap();
	}

	/**
	 * 生成评价的流程节点，按评价顺序,有低级到高级
	 */
	private void initEvaluationList() throws Exception {
		evaluateList = Lists.newArrayList(personEnd);
		if (personStart.equals(personEnd))
			return;

		TreeNode personStartNode = personConfigContext.getTaskPersonTreeNode();
		TreeNode presonEndNode = null;
		for (TreeNode node : MyUtil.getAllSubTreeNode(personStartNode))
			if (personEnd.getOperator().equals(
					taskConfigContext.getOperator(node)))
				presonEndNode = node;
		addEvaluateList(presonEndNode.getParent());
	}

	private void addEvaluateList(TreeNode node) {
		Person person = evaluationService.findOnePerson(task,
				taskConfigContext.getOperator(node));
		evaluateList.add(person);
		if (person.equals(personStart))
			return;
		addEvaluateList(node.getParent());
	}

	private void initLevelEvaluations() throws Exception {
		Map<Person, LevelEvaluation> map = MyUtil.map(
				personEnd.getEvaluations(template), "personStart");
		for (Person person : evaluateList) {
			LevelEvaluation levelEvaluation = map.get(person);
			if (levelEvaluation == null)
				break;
			levelEvaluation.getPersonStart(template);
			getLevelEvaluations().add(levelEvaluation);
		}
		if (map.get(personStart) == null) {
			LevelEvaluation levelEvaluation = new LevelEvaluation();
			levelEvaluation.setPersonStart(personStart);
			levelEvaluation.setPersonEnd(personEnd);
			getLevelEvaluations().add(levelEvaluation);
		}
	}

	private void initKpiEvaluationMap() throws Exception {
		for (Kpi kpi : personEnd.getKpis(template)) {
			Map<Person, KpiEvaluation> map = MyUtil.map(
					kpi.getKpiEvaluations(template), "person");
			for (Person person : evaluateList) {
				KpiEvaluation kpiEvaluation = map.get(person);
				if (kpiEvaluation == null)
					break;
				kpiEvaluation.getPerson(template);
				getKpiEvaluationMap().put(kpi, kpiEvaluation);
			}
			if (map.get(personStart) == null) {
				KpiEvaluation kpiEvaluation = new KpiEvaluation();
				kpiEvaluation.setPerson(personStart);
				kpiEvaluation.setKpi(kpi);
				getKpiEvaluationMap().put(kpi, kpiEvaluation);
			}
		}
	}

	@Override
	public List<LevelEvaluation> getLevelEvaluations() {
		if (levelEvaluations == null)
			levelEvaluations = Lists.newArrayList();
		return levelEvaluations;
	}

	@Override
	public Multimap<Kpi, KpiEvaluation> getKpiEvaluationMap() {
		if (kpiEvaluationMap == null)
			kpiEvaluationMap = ArrayListMultimap.create();
		return kpiEvaluationMap;
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
	public void evaluationPerson(Person person) throws Exception {
		personEnd = person;
	}

	@Override
	public Person getPersonStart() {
		return personStart;
	}

	@Override
	public Person getPersonEnd() {
		return personEnd;
	}

	@Override
	public void save() throws Exception {
		evaluationService.evaluate(getPersonStart(), getPersonEnd(),
				getLevelEvaluation(), getKpiEvaluations());
	}

	private LevelEvaluation getLevelEvaluation() throws Exception {
		Map<Person, LevelEvaluation> map = MyUtil.map(getLevelEvaluations(),
				"personStart");
		return map.get(getPersonStart());
	}

	private Iterable<KpiEvaluation> getKpiEvaluations() throws Exception {
		List<KpiEvaluation> result = Lists.newArrayList();
		Multimap<Kpi, KpiEvaluation> multiMap = getKpiEvaluationMap();
		for (Kpi kpi : multiMap.keySet()) {
			Map<Person, KpiEvaluation> map = MyUtil.map(multiMap.get(kpi),
					"person");
			result.add(map.get(getPersonStart()));
		}
		return result;
	}
}
