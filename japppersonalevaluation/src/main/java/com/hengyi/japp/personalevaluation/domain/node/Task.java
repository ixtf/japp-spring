package com.hengyi.japp.personalevaluation.domain.node;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Sets;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.domain.data.Modifiable;
import com.hengyi.japp.personalevaluation.domain.data.TaskStatus;
import com.hengyi.japp.personalevaluation.domain.relationship.FinalEvaluation;

@NodeEntity
public class Task extends Modifiable {
	private static final long serialVersionUID = -4044577316114353703L;
	public static final String TASK_PERSON = "TASK_PERSON";
	@RelatedTo(type = TaskConfig.TASKCONFIG_TASK, direction = Direction.INCOMING)
	private TaskConfig config;
	@RelatedTo(type = TASK_PERSON, elementClass = Person.class)
	private Set<Person> persons;
	@RelatedTo(type = Operator.OPERATOR_TASK, direction = Direction.INCOMING, elementClass = Operator.class)
	private Set<Operator> chargers;
	@RelatedToVia(type = FinalEvaluation.RELATIONSHIP, elementClass = FinalEvaluation.class)
	private Iterable<FinalEvaluation> finalEvaluations;
	@Min(Constant._YEAR)
	@Max(9999)
	@Indexed(indexType = IndexType.SIMPLE)
	private int year;
	@NotBlank
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "taskNameSearch")
	private String name;
	private TaskStatus status;

	public TaskConfig getConfig() {
		if (config == null)
			config = new TaskConfig(this);
		return config;
	}

	public void setConfig(TaskConfig config) {
		this.config = config;
	}

	public Iterable<Person> getPersons() {
		if (persons == null)
			persons = Sets.newHashSet();
		return persons;
	}

	public Iterable<Operator> getChargers() {
		if (chargers == null)
			chargers = Sets.newHashSet();
		return chargers;
	}

	public void setPersons(Iterable<Person> persons) {
		this.persons = Sets.newHashSet(persons);
	}

	public void setChargers(Iterable<Operator> chargers) {
		this.chargers = Sets.newHashSet(chargers);
	}

	public int getYear() {
		if (year < Constant._YEAR)
			year = new DateTime().getYear();
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TaskStatus getStatus() {
		if (status == null)
			status = TaskStatus.INIT;
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Iterable<FinalEvaluation> getFinalEvaluations() {
		if (finalEvaluations == null)
			finalEvaluations = Sets.newHashSet();
		return finalEvaluations;
	}

	public void setFinalEvaluations(Iterable<FinalEvaluation> finalEvaluations) {
		this.finalEvaluations = finalEvaluations;
	}

	public boolean isInit() {
		return status.equals(TaskStatus.INIT);
	}

	public boolean isActive() {
		return status.equals(TaskStatus.ACTIVE);
	}

	public boolean isFinish() {
		return status.equals(TaskStatus.FINISH);
	}

	public TaskConfig getConfig(Neo4jOperations template) {
		return template.fetch(getConfig());
	}

	public Iterable<Operator> getChargers(Neo4jOperations template) {
		return template.fetch(getChargers());
	}

	@Override
	public String toString() {
		return new StringBuilder().append(getYear()).append("-")
				.append(getName()).toString();
	}

	public Iterable<Person> getPersons(Neo4jOperations template) {
		return template.fetch(getPersons());
	}
}
