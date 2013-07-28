package com.hengyi.japp.personalevaluation.domain.node;

import java.util.Date;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.personalevaluation.domain.data.Level;
import com.hengyi.japp.personalevaluation.domain.data.PersonStatus;
import com.hengyi.japp.personalevaluation.domain.relationship.FinalEvaluation;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;

@NodeEntity
public class Person extends AbstractNeo4j {
	@NotBlank
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "personWorkSearch")
	private String work;
	private PersonStatus status;
	private boolean statistic;
	private boolean evaluable;
	@RelatedTo(type = Operator.OPERATOR_PERSON, direction = Direction.INCOMING)
	@Fetch
	private Operator operator;
	@RelatedTo(type = Task.TASK_PERSON, direction = Direction.INCOMING)
	private Task task;
	// 他对别人的评价
	@RelatedToVia(type = LevelEvaluation.RELATIONSHIP, elementClass = LevelEvaluation.class)
	private Set<LevelEvaluation> evaluations;
	// 别人对他的评价
	@RelatedToVia(type = LevelEvaluation.RELATIONSHIP, direction = Direction.INCOMING, elementClass = LevelEvaluation.class)
	private Set<LevelEvaluation> levelEvaluations;
	@RelatedTo(type = Kpi.KPI_PERSON, direction = Direction.INCOMING, elementClass = Kpi.class)
	private Set<Kpi> kpis;
	// 他对别人KPI的评价
	@RelatedToVia(type = KpiEvaluation.RELATIONSHIP, elementClass = KpiEvaluation.class)
	private Set<KpiEvaluation> kpiEvaluations;
	// 别人对他最终评价
	@RelatedToVia(type = FinalEvaluation.RELATIONSHIP, direction = Direction.INCOMING)
	private FinalEvaluation finalEvaluation;

	public LevelEvaluation evaluateLevel(Neo4jOperations template,
			Person person, Level level, String summary) {
		LevelEvaluation evaluation = template.createRelationshipBetween(this,
				person, LevelEvaluation.class, LevelEvaluation.RELATIONSHIP,
				false);
		evaluation.setDate(new Date());
		evaluation.setLevel(level);
		evaluation.setSummary(summary);
		template.save(evaluation);
		return evaluation;
	}

	public KpiEvaluation evaluateKpi(Neo4jOperations template, Kpi kpi,
			double score) {
		KpiEvaluation evaluation = template.createRelationshipBetween(this,
				kpi, KpiEvaluation.class, KpiEvaluation.RELATIONSHIP, false);
		evaluation.setDate(new Date());
		evaluation.setScore(score);
		template.save(evaluation);
		return evaluation;
	}

	public boolean isInit() {
		return getStatus().equals(PersonStatus.INIT);
	}

	public boolean isSave() {
		return getStatus().equals(PersonStatus.SAVE);
	}

	public boolean isSubmit() {
		return getStatus().equals(PersonStatus.SUBMIT);
	}

	public String getName() {
		return operator.getName();
	}

	public String getUuid() {
		return operator.getUuid();
	}

	public boolean addKpi(Kpi kpi) {
		return kpis.add(kpi);
	}

	public boolean removeKpi(Kpi kpi) {
		return kpis.remove(kpi);
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getWork() {
		if (work == null)
			work = "";
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public PersonStatus getStatus() {
		if (status == null)
			status = PersonStatus.INIT;
		return status;
	}

	public void setStatus(PersonStatus status) {
		this.status = status;
	}

	public boolean isStatistic() {
		return statistic;
	}

	public void setStatistic(boolean statistic) {
		this.statistic = statistic;
	}

	public boolean isEvaluable() {
		return evaluable;
	}

	public void setEvaluable(boolean evaluable) {
		this.evaluable = evaluable;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Iterable<LevelEvaluation> getEvaluations() {
		if (evaluations == null)
			evaluations = Sets.newHashSet();
		return evaluations;
	}

	public void setEvaluations(Set<LevelEvaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Iterable<LevelEvaluation> getLevelEvaluations() {
		if (levelEvaluations == null)
			levelEvaluations = Sets.newHashSet();
		return levelEvaluations;
	}

	public void setLevelEvaluations(Set<LevelEvaluation> levelEvaluations) {
		this.levelEvaluations = levelEvaluations;
	}

	public Set<Kpi> getKpis() {
		return kpis;
	}

	public void setKpis(Iterable<Kpi> kpis) {
		this.kpis = Sets.newHashSet(kpis);
	}

	public Iterable<KpiEvaluation> getKpiEvaluations() {
		if (kpiEvaluations == null)
			kpiEvaluations = Sets.newHashSet();
		return kpiEvaluations;
	}

	public void setKpiEvaluations(Iterable<KpiEvaluation> kpiEvaluations) {
		this.kpiEvaluations = Sets.newHashSet(kpiEvaluations);
	}

	public FinalEvaluation getFinalEvaluation() {
		return finalEvaluation;
	}

	public void setFinalEvaluation(FinalEvaluation finalEvaluation) {
		this.finalEvaluation = finalEvaluation;
	}

	public Person() {
		super();
	}

	public Person(Operator operator) {
		super();
		setOperator(operator);
	}

	public Set<Kpi> getKpis(Neo4jOperations template) {
		return template.fetch(getKpis());
	}

	@Override
	public String toString() {
		if (getOperator() == null)
			return super.toString();
		return getOperator().toString();
	}

	public Iterable<LevelEvaluation> getEvaluations(Neo4jOperations template) {
		return template.fetch(getEvaluations());
	}

	public Iterable<LevelEvaluation> getLevelEvaluations(
			Neo4jOperations template) {
		return template.fetch(getLevelEvaluations());
	}

	public Task getTask(Neo4jOperations template) {
		return template.fetch(getTask());
	}
}
