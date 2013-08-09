package com.hengyi.japp.personalevaluation.context;

import org.primefaces.model.TreeNode;

import com.hengyi.japp.personalevaluation.domain.node.Operator;

public interface TaskConfigPersonContext {
	TaskConfigContext getTaskConfigContext();

	Operator getOperator() throws Exception;

	TreeNode getEvaluationPersonTree() throws Exception;

	TreeNode getTaskPersonTreeNode() throws Exception;

	TaskConfigPersonTreeNode getTaskPerson();
}
