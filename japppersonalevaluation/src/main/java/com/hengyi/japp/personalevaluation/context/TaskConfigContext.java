package com.hengyi.japp.personalevaluation.context;

import java.util.List;

import org.primefaces.model.TreeNode;

import com.hengyi.japp.personalevaluation.domain.data.TaskConfigKpi;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;

public interface TaskConfigContext {
	TaskConfigPersonContext taskConfigPersonContext(Operator operator)
			throws Exception;

	Task getTask();

	TaskConfig getTaskConfig();

	List<Operator> getChargers();

	void addCharger(Operator charger);

	void removeCharger(Operator charger);

	List<TaskConfigKpi> getTaskKpis();

	void addTaskKpi(TaskConfigKpi kpi);

	void removeTaskKpi(TaskConfigKpi kpi);

	TreeNode getTaskPersonTree();

	TreeNode getTaskPersonTreeNode();

	void setTaskPersonTreeNode(TreeNode taskPersonTreeNode);

	void addTaskPerson(Operator operator) throws Exception;

	void removeTaskPerson() throws Exception;

	void save() throws Exception;

	Operator getOperator(TreeNode node);
}
