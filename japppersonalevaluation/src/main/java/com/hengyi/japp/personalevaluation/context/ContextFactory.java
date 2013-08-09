package com.hengyi.japp.personalevaluation.context;

import org.primefaces.model.TreeNode;

import com.google.common.collect.BiMap;
import com.hengyi.japp.personalevaluation.domain.node.Person;

public interface ContextFactory {
	TaskConfigContext taskConfigContext() throws Exception;

	TaskConfigContext taskConfigContext(final Long taskNodeId) throws Exception;

	TaskConfigPersonContext taskConfigPersonContext(
			TaskConfigContext taskConfigContext,
			BiMap<TaskConfigPersonTreeNode, TreeNode> treeNodeMap,
			TreeNode taskPersonTreeNode) throws Exception;

	EvaluationContext evaluationContext() throws Exception;

	EvaluationContext evaluationContext(Long taskNodeId, Long operatorNodeId)
			throws Exception;

	EvaluationPersonContext evaluationPersonContext(
			EvaluationContext evaluationContext, Person personEnd)
			throws Exception;
}
