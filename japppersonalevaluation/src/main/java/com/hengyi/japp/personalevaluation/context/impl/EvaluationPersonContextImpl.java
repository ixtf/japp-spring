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
import com.google.common.eventbus.EventBus;
import com.hengyi.japp.personalevaluation.context.EvaluationContext;
import com.hengyi.japp.personalevaluation.context.EvaluationPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.domain.node.Kpi;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;
import com.hengyi.japp.personalevaluation.event.EvaluationEvent;
import com.hengyi.japp.personalevaluation.service.CacheServiceFacade;
import com.hengyi.japp.personalevaluation.service.EvaluationService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.utils.MyUtil;

public class EvaluationPersonContextImpl implements EvaluationPersonContext {
	private final TaskConfigContext taskConfigContext;
	private final TaskConfigPersonContext personConfigContext;
	private final EvaluationContext evaluationContext;
	private Neo4jOperations template;
	private EventBus eventBus;
	private EvaluationService evaluationService;

	private Person personStart;
	private Person personEnd;
	// personEnd的评价数据提交顺序
	private List<Person> evaluateList;
	// private List<Long> evaluateList;
	private List<LevelEvaluation> levelEvaluations;
	private Multimap<Kpi, KpiEvaluation> kpiEvaluationMap;

	public EvaluationPersonContextImpl(@NotNull Person personEnd,
			EvaluationContext evaluationContext, Neo4jOperations template,
			Mapper dozer, EventBus eventBus, CacheServiceFacade cacheService,
			OperatorService operatorService, EvaluationService evaluationService)
			throws Exception {
		this.taskConfigContext = evaluationContext.getTaskConfigContext();
		this.personConfigContext = evaluationContext
				.getTaskConfigPersonContext();
		this.evaluationContext = evaluationContext;
		this.template = template;
		this.eventBus = eventBus;
		this.evaluationService = evaluationService;
		personStart = evaluationContext.getPerson();
		this.personEnd = personEnd;

		// evaluateList = MyUtil.getEvaluateList(taskConfig, personEnd);
		initEvaluationList();
		initLevelEvaluations();
		initKpiEvaluationMap();
	}

	/**
	 * 生成评价的流程节点，按评价顺序,由低级到高级
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
		Person person = evaluationService.findOnePerson(getTask(),
				taskConfigContext.getOperator(node));
		evaluateList.add(person);
		if (person.equals(personStart))
			return;
		addEvaluateList(node.getParent());
	}

	private void initLevelEvaluations() throws Exception {
		Map<Person, LevelEvaluation> map = MyUtil.map(
				personEnd.getLevelEvaluations(template), "personStart");
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
		return evaluationContext.getTask();
	}

	@Override
	public TaskConfig getTaskConfig() {
		return evaluationContext.getTaskConfig();
	}

	@Override
	public EvaluationContext getEvaluationContext() {
		return evaluationContext;
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
		eventBus.post(new EvaluationEvent(getPersonStart(), getPersonEnd()));
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
