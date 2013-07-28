package com.hengyi.japp.personalevaluation.web;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.personalevaluation.domain.node.Task;

@Named
@Scope("request")
public class TaskUpdateController extends AbstractController {
	private static final long serialVersionUID = -3947884480985990263L;
	private Long nodeId;
	private Task task;

	public void save() {
		try {
			taskRepository.save(task);
			redirect("/tasks/" + getTask().getNodeId() + "/config/chargers");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	@PostConstruct
	public void init() {
		task = new Task();
	}

	public Long getNodeId() {
		return nodeId;
	}

	public Task getTask() {
		return task;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
		task = taskRepository.findOne(getNodeId());
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
