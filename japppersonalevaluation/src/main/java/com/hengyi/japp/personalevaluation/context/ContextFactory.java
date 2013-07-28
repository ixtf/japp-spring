package com.hengyi.japp.personalevaluation.context;

import org.primefaces.model.TreeNode;

import com.google.common.collect.BiMap;

public interface ContextFactory {
	TaskConfigContext taskConfigContext(final Long taskNodeId) throws Exception;

	TaskConfigPersonContext taskConfigPersonContext(
			TaskConfigContext taskConfigContext,
			BiMap<TaskConfigPersonTreeNode, TreeNode> treeNodeMap,
			TreeNode taskPersonTreeNode) throws Exception;

	EvaluationContext evaluationContext() throws Exception;

	EvaluationPersonContext evaluationPersonContext(final Long personNodeId)
			throws Exception;
}
