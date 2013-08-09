package com.hengyi.japp.personalevaluation.web;

import java.util.List;

import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonTreeNode;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigKpi;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.SubmitLimit;
import com.hengyi.japp.personalevaluation.domain.node.Task;

@Named
@Scope("session")
public class TaskConfigController extends AbstractController {
	private static final long serialVersionUID = 4456255104731446014L;
	@NotNull
	@Min(1)
	protected Long taskNodeId;
	private TaskConfigContext taskConfigContext;
	private TaskConfigPersonContext personConfigContext;

	private Operator charger;
	private TaskConfigKpi kpi;
	// 选中的HR员工
	private List<String> empSns;
	// 选中的提交限制
	private SubmitLimit submitLimit;
	private SubmitLimit submitLimitTest;

	public void setTaskNodeId(Long taskNodeId) {
		if (taskNodeId.equals(this.taskNodeId))
			return;
		this.taskNodeId = taskNodeId;
		try {
			taskService.checkCharger(taskNodeId);
			taskConfigContext = contextFactory
					.taskConfigContext(getTaskNodeId());
		} catch (Exception e) {
			addErrorMessage(e);
			taskNodeId = null;
			taskConfigContext = null;
			redirect("tasks");
		}
	}

	public void save() {
		try {
			taskConfigContext.save();
			taskNodeId = null;
			taskConfigContext = null;
			personConfigContext = null;
			redirect("tasks");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public List<Operator> getChargers() {
		return taskConfigContext.getChargers();
	}

	public void addCharger() throws Exception {
		setCharger(operatorService.findByEmpsn(getCharger().getEmpSn()));
		taskConfigContext.addCharger(getCharger());
		setCharger(null);
	}

	public void removeCharger() {
		taskConfigContext.removeCharger(getCharger());
		setCharger(null);
	}

	public List<TaskConfigKpi> getTaskKpis() {
		return taskConfigContext.getTaskKpis();
	}

	public void addTaskKpi() {
		taskConfigContext.addTaskKpi(getKpi());
		setKpi(null);
	}

	public void removeTaskKpi() {
		taskConfigContext.removeTaskKpi(getKpi());
		setKpi(null);
	}

	public TreeNode getTaskPersonTree() throws Exception {
		return taskConfigContext.getTaskPersonTree();
	}

	public void addTaskPerson() throws Exception {
		for (String empSn : getEmpSns())
			taskConfigContext.addTaskPerson(operatorService.findByEmpsn(empSn));
		getEmpSns().clear();
	}

	public void addHrOrganizationUsers() throws Exception {
		for (BindUserDTO user : getHrOrganizationUsers())
			taskConfigContext.addTaskPerson(operatorService.findByEmpsn(user
					.getPrincipal()));
		getEmpSns().clear();
	}

	public void removeTaskPerson() throws Exception {
		taskConfigContext.removeTaskPerson();
	}

	public void configTaskPerson() {
		try {
			personConfigContext = taskConfigContext
					.taskConfigPersonContext(taskConfigContext
							.getOperator(taskConfigContext
									.getTaskPersonTreeNode()));
			redirect("/tasks/" + getTaskNodeId() + "/config/person");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public TreeNode getEvaluationPersonTree() throws Exception {
		return personConfigContext.getEvaluationPersonTree();
	}

	public TaskConfigPersonTreeNode getTaskPerson() {
		return personConfigContext.getTaskPerson();
	}

	public List<TaskConfigKpi> getPersonKpis() {
		return Lists.newArrayList(getTaskPerson().getKpis());
	}

	public void addPersonKpi() {
		personConfigContext.getTaskPerson().addKpi(getKpi());
		setKpi(null);
	}

	public void removePersonKpi() {
		personConfigContext.getTaskPerson().removeKpi(getKpi());
		setKpi(null);
	}

	public List<SubmitLimit> getSubmitLimits() {
		return Lists.newArrayList(getTaskPerson().getSubmitLimits());
	}

	public void addSubmitLimit() {
		getTaskPerson().addSubmitLimit(getSubmitLimit());
		setSubmitLimit(null);
	}

	public void removeSubmitLimit() {
		getTaskPerson().removeSubmitLimit(getSubmitLimit());
		setSubmitLimit(null);
	}

	public void updateTaskPerson() {
		redirect("/tasks/" + getTaskNodeId() + "/config/persons");
	}

	public Operator getCharger() {
		if (charger == null)
			charger = new Operator();
		return charger;
	}

	public TreeNode getTaskPersonTreeNode() {
		return taskConfigContext.getTaskPersonTreeNode();
	}

	public TaskConfigKpi getKpi() {
		if (kpi == null)
			kpi = new TaskConfigKpi();
		return kpi;
	}

	public void setCharger(Operator charger) {
		this.charger = charger;
	}

	public void setKpi(TaskConfigKpi kpi) {
		this.kpi = kpi;
	}

	public Long getTaskNodeId() {
		return taskNodeId;
	}

	public Task getTask() {
		return taskConfigContext.getTask();
	}

	public void setTaskPersonTreeNode(TreeNode taskPersonTreeNode) {
		taskConfigContext.setTaskPersonTreeNode(taskPersonTreeNode);
	}

	public List<String> getEmpSns() {
		return empSns;
	}

	public void setEmpSns(List<String> empSns) {
		this.empSns = empSns;
	}

	public SubmitLimit getSubmitLimit() {
		if (submitLimit == null)
			submitLimit = new SubmitLimit();
		return submitLimit;
	}

	public void setSubmitLimit(SubmitLimit submitLimit) {
		this.submitLimit = submitLimit;
	}

	public SubmitLimit getSubmitLimitTest() {
		return submitLimitTest;
	}

	public void setSubmitLimitTest(SubmitLimit submitLimitTest) {
		this.submitLimitTest = submitLimitTest;
	}
}
