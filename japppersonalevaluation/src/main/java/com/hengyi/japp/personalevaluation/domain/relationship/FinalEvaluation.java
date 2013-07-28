package com.hengyi.japp.personalevaluation.domain.relationship;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;

@RelationshipEntity
public class FinalEvaluation extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -8510160652693915411L;
	public static final String RELATIONSHIP = "FINAL_EVALUATION";
	@StartNode
	private Task task;
	@EndNode
	private Person person;
	/* 用户的nodeId来记录，避免考核负责人不参加考核的情况作出最终评价 */
	private Long userNodeId;
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getUserNodeId() {
		return userNodeId;
	}

	public void setUserNodeId(Long userNodeId) {
		this.userNodeId = userNodeId;
	}

	public Task getTask() {
		return task;
	}

	public Person getPerson() {
		return person;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
