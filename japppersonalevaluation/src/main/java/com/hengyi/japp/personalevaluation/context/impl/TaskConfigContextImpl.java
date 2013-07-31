package com.hengyi.japp.personalevaluation.context.impl;

import java.util.Map;
import java.util.Set;

import org.dozer.Mapper;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.hengyi.japp.personalevaluation.context.ContextFactory;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonContext;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonTreeNode;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigPerson;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.service.CacheServiceFacade;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.service.TaskService;
import com.hengyi.japp.personalevaluation.utils.MyUtil;

public class TaskConfigContextImpl extends AbstractTaskConfigContext {
	private TreeNode taskPersonTree;
	private TreeNode rootTreeNode;
	/**
	 * 保存所有考核人员的详细配置信息
	 */
	private BiMap<TaskConfigPersonTreeNode, TreeNode> treeNodeMap;
	/**
	 * 单个人详细配置，所选中的Person节点
	 */
	private TreeNode taskPersonTreeNode;

	public TaskConfigContextImpl(Long taskNodeId,
			ContextFactory contextFactory, Neo4jOperations template,
			Mapper dozer, CacheServiceFacade cacheService,
			OperatorService operatorService, TaskService taskService)
			throws Exception {
		super(taskNodeId, contextFactory, template, dozer, cacheService,
				operatorService, taskService);
		treeNodeMap = HashBiMap.<TaskConfigPersonTreeNode, TreeNode> create();
		for (TaskConfigPerson taskPerson : getTaskConfig().getTaskPersons()) {
			TaskConfigPersonTreeNode data = dozer.map(taskPerson,
					TaskConfigPersonTreeNode.class);
			Operator operator = operatorService.findOne(data.getNodeId());
			Operator manager = operatorService.findOne(data.getManagerNodeId());
			data.setOperator(operator);
			data.setManager(manager);
			treeNodeMap.put(data, new DefaultTreeNode(data, rootTreeNode));
		}
		initTaskPersonTree();
	}

	/**
	 * 根据treeNodeMap的数据重新初始化tree
	 * 
	 * @throws Exception
	 */
	private void initTaskPersonTree() throws Exception {
		taskPersonTree = new DefaultTreeNode("Tree", null);
		taskPersonTree.setSelectable(false);
		rootTreeNode = MyUtil.tree(treeNodeMap, "nodeId", "managerNodeId");
		rootTreeNode.setParent(taskPersonTree);
		rootTreeNode.setExpanded(true);
		for (TreeNode node : MyUtil.getAllParentNode(taskPersonTreeNode))
			node.setExpanded(true);
		if (taskPersonTreeNode != null)
			taskPersonTreeNode.setExpanded(true);
	}

	@Override
	void updateConfig() throws Exception {
		getTaskConfig().setTaskPersons(treeNodeMap.keySet());
	}

	@Override
	public TreeNode getTaskPersonTree() {
		return taskPersonTree;
	}

	@Override
	public void addTaskPerson(Operator operator) throws Exception {
		Map<Long, TaskConfigPersonTreeNode> map = MyUtil.map(
				treeNodeMap.keySet(), "nodeId");
		TaskConfigPersonTreeNode data = map.get(operator.getNodeId());
		if (data == null) {
			data = new TaskConfigPersonTreeNode(operator);
			treeNodeMap.put(data, new DefaultTreeNode(data, rootTreeNode));
		}
		Set<TreeNode> pNodes = MyUtil.getAllParentNode(taskPersonTreeNode);
		pNodes.add(taskPersonTreeNode);
		TreeNode node = treeNodeMap.get(data);
		if (!pNodes.contains(node))
			data.setManager(getOperator(taskPersonTreeNode));
		initTaskPersonTree();
	}

	@Override
	public void removeTaskPerson() throws Exception {
		if (taskPersonTreeNode.equals(rootTreeNode)) {
			treeNodeMap.clear();
		} else {
			for (TreeNode node : MyUtil.getAllSubTreeNode(taskPersonTreeNode))
				treeNodeMap.inverse().remove(node);
			treeNodeMap.inverse().remove(taskPersonTreeNode);
			taskPersonTreeNode.getParent().getChildren()
					.remove(taskPersonTreeNode);
		}
		initTaskPersonTree();
	}

	@Override
	public Operator getOperator(TreeNode node) {
		TaskConfigPersonTreeNode data = treeNodeMap.inverse().get(node);
		return data == null ? null : data.getOperator();
	}

	@Override
	public TreeNode getTaskPersonTreeNode() {
		return taskPersonTreeNode;
	}

	@Override
	public void setTaskPersonTreeNode(TreeNode taskPersonTreeNode) {
		this.taskPersonTreeNode = taskPersonTreeNode;
	}

	@Override
	public TaskConfigPersonContext taskConfigPersonContext(Operator operator)
			throws Exception {
		Map<Long, TaskConfigPersonTreeNode> map = MyUtil.map(
				treeNodeMap.keySet(), "nodeId");
		return contextFactory.taskConfigPersonContext(this, treeNodeMap,
				treeNodeMap.get(map.get(operator.getNodeId())));
	}
}
