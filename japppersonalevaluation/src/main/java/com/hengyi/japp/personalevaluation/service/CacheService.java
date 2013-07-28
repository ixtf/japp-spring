package com.hengyi.japp.personalevaluation.service;

import java.util.List;

import org.primefaces.model.TreeNode;

import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Task;

public interface CacheService {
	<T> T getSession(Object key, Class<T> value) throws Exception;

	void setSession(Object key, Object value) throws Exception;

	Operator getCurrentOperator() throws Exception;

	Task getCurrentTask() throws Exception;

	TreeNode getHrOrganizationTree() throws Exception;

	List<BindUserDTO> getHrUsers(TreeNode hrOrganizationTreeNode);
}
