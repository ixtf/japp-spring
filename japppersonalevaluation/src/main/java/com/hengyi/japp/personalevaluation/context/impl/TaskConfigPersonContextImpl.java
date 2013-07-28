package com.hengyi.japp.personalevaluation.context.impl;

import java.util.Set;

import org.primefaces.model.TreeNode;

import com.google.common.collect.BiMap;
import com.google.common.collect.Sets;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.context.TaskConfigContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonTreeNode;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.utils.MyUtil;

public class TaskConfigPersonContextImpl implements TaskConfigPersonContext {
	private final TaskConfigContext taskConfigContext;
	private final BiMap<TaskConfigPersonTreeNode, TreeNode> treeNodeMap;
	private final TreeNode taskPersonTreeNode;

	/**
	 * 当前节点，及下属节点，包括所有的父节点
	 */
	private TreeNode evaluationPersonTree;
	/**
	 * 当前考核人员的所有详细配置
	 */
	private TaskConfigPersonTreeNode taskPerson;
	/**
	 * 考核人员所要考核的层级，如有变化，重新初始化 evaluationPersonTree
	 */
	private int lays;

	public TaskConfigPersonContextImpl(TaskConfigContext taskConfigContext,
			BiMap<TaskConfigPersonTreeNode, TreeNode> treeNodeMap,
			TreeNode taskPersonTreeNode) throws Exception {
		this.taskConfigContext = taskConfigContext;
		this.treeNodeMap = treeNodeMap;
		this.taskPersonTreeNode = taskPersonTreeNode;
		lays = getTaskPerson().getLays();
		initEvaluationPersonTree();
	}

	private void initEvaluationPersonTree() throws Exception {
		Set<TaskConfigPersonTreeNode> datas = getTreeNodeDatas(MyUtil
				.getSubTreeNode(taskPersonTreeNode, getTaskPerson().getLays()));
		datas.addAll(getTreeNodeDatas(MyUtil
				.getAllParentNode(taskPersonTreeNode)));
		evaluationPersonTree = MyUtil.tree(datas, "nodeId", "managerNodeId");
		evaluationPersonTree.setExpanded(true);
		for (TreeNode node : MyUtil.getAllSubTreeNode(evaluationPersonTree)) {
			node.setExpanded(true);
			if (node.equals(taskPersonTreeNode))
				node.setSelected(true);
		}
	}

	private Set<TaskConfigPersonTreeNode> getTreeNodeDatas(
			Iterable<TreeNode> nodes) {
		Set<TaskConfigPersonTreeNode> result = Sets.newHashSet();
		for (TreeNode node : nodes) {
			TaskConfigPersonTreeNode data = treeNodeMap.inverse().get(node);
			if (data != null)
				result.add(data);
		}
		return result;
	}

	@Override
	public TreeNode getEvaluationPersonTree() throws Exception {
		if (lays != getTaskPerson().getLays()) {
			initEvaluationPersonTree();
			lays = getTaskPerson().getLays();
		}
		return evaluationPersonTree;
	}

	/**
	 * 获取当前节点，及下属节点，不包括所有的父节点
	 */
	@Override
	public TreeNode getTaskPersonTreeNode() throws Exception {
		for (TreeNode node : MyUtil
				.getAllSubTreeNode(getEvaluationPersonTree()))
			if (taskPersonTreeNode.equals(node))
				return node;
		throw new Exception(Constant.ErrorCode.SYSTEM);
	}

	@Override
	public TaskConfigPersonTreeNode getTaskPerson() {
		if (taskPerson == null)
			taskPerson = treeNodeMap.inverse().get(taskPersonTreeNode);
		return taskPerson;
	}

	@Override
	public Operator getOperator() throws Exception {
		return getTaskPerson().getOperator();
	}

	@Override
	public TaskConfigContext getTaskConfigContext() {
		return taskConfigContext;
	}
}
