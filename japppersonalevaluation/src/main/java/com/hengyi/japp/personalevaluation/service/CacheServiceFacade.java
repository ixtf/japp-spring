package com.hengyi.japp.personalevaluation.service;

import java.util.List;

import org.primefaces.model.TreeNode;

import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.common.service.CacheService;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Task;

public interface CacheServiceFacade extends CacheService {
	Operator getCurrentOperator() throws Exception;

	Task getCurrentTask() throws Exception;

	TreeNode getHrOrganizationTree() throws Exception;

	List<BindUserDTO> getHrUsers(TreeNode hrOrganizationTreeNode);
}
