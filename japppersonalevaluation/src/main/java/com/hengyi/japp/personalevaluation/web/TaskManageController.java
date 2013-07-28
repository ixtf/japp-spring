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
public class TaskManageController extends AbstractController {
	private static final long serialVersionUID = 4456255104731446014L;
	private List<Task> tasks;

	private int year;
	private Task task;

	@PostConstruct
	public void search() {
		try {
			tasks = Lists.newArrayList();
			for (Task task : taskService.findAllByCharger(getYear()))
				tasks.add(task);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void config() {
		redirect("/tasks/" + getTask().getNodeId() + "/config/chargers");
	}

	public void active() {
		try {
			taskService.activeTask(getTask().getNodeId());
			addInfoMessage("任务激活成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void finish() {
		try {
			taskService.finishTask(getTask().getNodeId());
			addInfoMessage("任务完成成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void delete() {
		try {
			taskService.deleteTask(getTask().getNodeId());
			addInfoMessage("任务完成成功！");
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
