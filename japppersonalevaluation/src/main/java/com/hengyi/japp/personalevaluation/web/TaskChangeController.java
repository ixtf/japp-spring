package com.hengyi.japp.personalevaluation.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.domain.node.Task;

@Named
@Scope("request")
// @Join(path = "/tasks", to = "/faces/task/list.jsf")
public class TaskChangeController extends AbstractController {
	private static final long serialVersionUID = 4456255104731446014L;
	private List<Task> tasks;

	private int year;
	private Task task;

	@PostConstruct
	public void search() {
		try {
			tasks = Lists.newArrayList();
			for (Task task : taskService.findAllByOperator(getYear()))
				tasks.add(task);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void change() {
		try {
			operatorService.updateLastTask(cacheService.getCurrentOperator()
					.getNodeId(), task.getNodeId());
			cacheService.setSession(Constant.SESSION_TASK, task);
			redirect("/");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public int getYear() {
		if (year < Constant._YEAR)
			year = new DateTime().getYear();
		return year;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
