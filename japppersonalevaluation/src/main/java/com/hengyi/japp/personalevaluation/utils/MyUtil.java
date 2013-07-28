package com.hengyi.japp.personalevaluation.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hengyi.japp.personalevaluation.context.TaskConfigPersonTreeNode;
import com.hengyi.japp.personalevaluation.domain.data.TaskConfigPerson;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;

@SuppressWarnings("unchecked")
public class MyUtil {
	public static <K, V> Map<K, V> map(Iterable<V> vs, String id)
			throws Exception {
		Map<K, V> result = Maps.newHashMap();
		for (V v : vs) {
			K k = (K) PropertyUtils.getSimpleProperty(v, id);
			result.put(k, v);
		}
		return result;
	}

	public static <T> Map<T, TreeNode> treeNodeMap(Iterable<T> datas) {
		Map<T, TreeNode> result = Maps.newHashMap();
		for (T data : datas)
			result.put(data, new DefaultTreeNode(data, null));
		return result;
	}

	public static <T> TreeNode tree(Iterable<T> datas, String id, String pId)
			throws Exception {
		return tree(treeNodeMap(datas), id, pId);
	}

	public static <T> TreeNode tree(Map<T, TreeNode> treeNodeMap, String id,
			String pId) throws Exception {
		TreeNode result = new DefaultTreeNode("Root", null);
		Map<Object, T> map = map(treeNodeMap.keySet(), id);
		for (Entry<T, TreeNode> entry : treeNodeMap.entrySet()) {
			T data = entry.getKey();
			TreeNode node = entry.getValue();

			Object pKey = PropertyUtils.getSimpleProperty(data, pId);
			TreeNode pNode = treeNodeMap.get(map.get(pKey));
			if (pNode != null)
				node.setParent(pNode);
			else
				node.setParent(result);
		}
		return result;
	}

	public static Map<Long, TaskConfigPersonTreeNode> getTreeNodeDataMap(
			Set<TaskConfigPersonTreeNode> sets) {
		Map<Long, TaskConfigPersonTreeNode> map = Maps.newHashMap();
		for (TaskConfigPersonTreeNode data : sets)
			map.put(data.getNodeId(), data);
		return map;
	}

	public static Set<TreeNode> getAllParentNode(TreeNode node) {
		Set<TreeNode> result = Sets.newHashSet();
		if (node == null)
			return result;
		TreeNode pNode = node.getParent();
		if (pNode == null)
			return result;
		result.add(pNode);
		result.addAll(getAllParentNode(pNode));
		return result;
	}

	/**
	 * 以 node 为根节点，往下找所有子节点
	 * 
	 * 不包括 node 本身
	 * 
	 * @param node
	 * @return
	 */
	public static Set<TreeNode> getAllSubTreeNode(TreeNode node) {
		Set<TreeNode> result = Sets.newHashSet();
		if (node.isLeaf())
			return result;
		for (TreeNode subNode : node.getChildren()) {
			result.add(subNode);
			result.addAll(getAllSubTreeNode(subNode));
		}
		return result;
	}

	/**
	 * 以 node 为根节点，往下找 lays 层的子节点
	 * 
	 * 不包括 node 本身
	 * 
	 * @param node
	 * @param lays
	 * @return
	 */
	public static Set<TreeNode> getSubTreeNode(TreeNode node, int lays) {
		Set<TreeNode> result = Sets.newHashSet(node);
		if (lays == TaskConfigPersonTreeNode.LAYS_ALL) {
			result.addAll(MyUtil.getAllSubTreeNode(node));
		} else {
			Set<TreeNode> _nodes = result;
			for (int i = 0; i < lays; i++) {
				_nodes = MyUtil.getSubTreeNode(_nodes);
				result.addAll(_nodes);
			}
		}
		return result;
	}

	public static Set<TreeNode> getSubTreeNode(Iterable<TreeNode> nodes) {
		Set<TreeNode> result = Sets.newHashSet();
		for (TreeNode node : nodes)
			if (!node.isLeaf())
				result.addAll(node.getChildren());
		return result;
	}

	public static List<Long> getEvaluateList(TaskConfig taskConfig,
			Person person) throws Exception {
		List<Long> result = Lists.newArrayList();
		Map<Long, TaskConfigPerson> map = MyUtil.map(
				taskConfig.getTaskPersons(), "nodeId");
		TaskConfigPerson taskConfigPerson = map.get(person.getOperator()
				.getNodeId());
		if (person.isEvaluable())
			result.add(taskConfigPerson.getNodeId());
		addEvaluateList(result, taskConfigPerson, map);
		return result;
	}

	private static void addEvaluateList(List<Long> result,
			TaskConfigPerson taskConfigPerson, Map<Long, TaskConfigPerson> map) {
		Long managerNodeId = taskConfigPerson.getManagerNodeId();
		if (managerNodeId == null)
			return;
		result.add(managerNodeId);
		addEvaluateList(result, map.get(managerNodeId), map);
	}
}
