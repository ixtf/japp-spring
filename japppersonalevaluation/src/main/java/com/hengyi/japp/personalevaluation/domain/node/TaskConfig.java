package com.hengyi.japp.personalevaluation.domain.node;

import static com.hengyi.japp.personalevaluation.Constant.JSON;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.hengyi.japp.personalevaluation.domain.data.Modifiable;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigKpi;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigPerson;
import com.hengyi.japp.personalevaluation.utils.JsonUtil;

@SuppressWarnings("unused")
@NodeEntity
public class TaskConfig extends Modifiable {
	private static final long serialVersionUID = 8602181337421698564L;
	private static final Logger log = LoggerFactory.getLogger(TaskConfig.class);
	public static final String TASKCONFIG_TASK = "TASK_CONFIG";
	// Iterable<TaskConfigKpi>
	private String taskKpisJson;
	// Iterable<TaskConfigPerson>
	private String taskPersonsJson;
	@RelatedTo(type = TASKCONFIG_TASK)
	@Fetch
	private Task task;

	public TaskConfig() {
		super();
	}

	public TaskConfig(Task task) {
		this();
		this.task = task;
	}

	public Iterable<TaskConfigPerson> getTaskPersons() throws Exception {
		return JsonUtil.convert(getTaskPersonsJson(), TaskConfigPerson.class);
	}

	public void setTaskPersons(Iterable<? extends TaskConfigPerson> taskPersons)
			throws Exception {
		setTaskPersonsJson(JSON.writeValueAsString(taskPersons));
		// TaskConfigPerson的传入可能是TaskConfigPersonTreeNode，String会很长，重新set一下，减少不必要String
		setTaskPersonsJson(JSON.writeValueAsString(getTaskPersons()));
	}

	public Iterable<TaskConfigKpi> getTaskKpis() throws Exception {
		return JsonUtil.convert(getTaskKpisJson(), TaskConfigKpi.class);
	}

	public TaskConfigPerson getTaskConfigPerson(Operator operator)
			throws Exception {
		Long nodeId = operator.getNodeId();
		for (TaskConfigPerson personConfig : getTaskPersons())
			if (personConfig.getNodeId().equals(nodeId))
				return personConfig;
		return new TaskConfigPerson(nodeId);
	}

	public void setTaskKpis(Iterable<TaskConfigKpi> kpis) throws Exception {
		setTaskKpisJson(JsonUtil.convert(kpis));
	}

	public String getTaskPersonsJson() {
		return taskPersonsJson == null ? JsonNodeFactory.instance.arrayNode()
				.toString() : taskPersonsJson;
	}

	public String getTaskKpisJson() {
		return taskKpisJson == null ? JsonNodeFactory.instance.arrayNode()
				.toString() : taskKpisJson;
	}

	public Task getTask() {
		return task;
	}

	public Task getTask(Neo4jOperations template) {
		return template.fetch(getTask());
	}

	public void setTaskPersonsJson(String taskPersonsJson) {
		this.taskPersonsJson = taskPersonsJson;
	}

	public void setTaskKpisJson(String taskKpisJson) {
		this.taskKpisJson = taskKpisJson;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
