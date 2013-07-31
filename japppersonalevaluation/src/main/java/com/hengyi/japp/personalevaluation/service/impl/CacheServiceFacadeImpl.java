package com.hengyi.japp.personalevaluation.service.impl;

import static com.hengyi.japp.common.Constant.ADMIN_NAME;
import static com.hengyi.japp.common.Constant.ADMIN_PRINCIPAL;
import static com.hengyi.japp.common.Constant.SESSION_OPERATOR;
import static com.hengyi.japp.personalevaluation.Constant.SESSION_TASK;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.primefaces.model.TreeNode;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.service.impl.CacheServiceImpl;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.repository.PersonRepository;
import com.hengyi.japp.personalevaluation.service.CacheServiceFacade;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.service.TaskService;
import com.hengyi.japp.personalevaluation.utils.MyUtil;

@Named("cacheService")
@Singleton
public class CacheServiceFacadeImpl extends CacheServiceImpl implements
		CacheServiceFacade {
	@Inject
	private OperatorService operatorService;
	@Inject
	private TaskService taskService;
	@Inject
	private PersonRepository personRepository;
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;

	@Override
	public Operator getCurrentOperator() throws Exception {
		Operator operator = getSession(SESSION_OPERATOR, Operator.class);
		if (operator != null)
			return operator;
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated())
			return operator;
		if (ADMIN_PRINCIPAL.equals(subject.getPrincipal()))
			operator = new Operator(ADMIN_PRINCIPAL, ADMIN_NAME);
		else
			operator = operatorService.findOne(getUser());
		setSession(SESSION_OPERATOR, operator);
		return operator;
	}

	@Override
	public Task getCurrentTask() throws Exception {
		Task task = getSession(SESSION_TASK, Task.class);
		if (task != null)
			return task;
		Operator operator = getCurrentOperator();
		if (operator != null)
			task = operator.getLastTask(taskService);
		if (task == null)
			task = taskService.getDefaultTask(operator);
		setSession(SESSION_TASK, task);
		return task;
	}

	private TreeNode hrOrganizationTree;

	@Override
	public TreeNode getHrOrganizationTree() throws Exception {
		return hrOrganizationTree != null ? hrOrganizationTree
				: getHrOrganizationTreeFromHr();
	}

	// @PostConstruct
	private synchronized TreeNode getHrOrganizationTreeFromHr()
			throws Exception {
		hrOrganizationTree = MyUtil.tree(
				jappCommonSoapClient.findAllHrOrganization(), "id", "pId");
		for (TreeNode node : hrOrganizationTree.getChildren())
			node.setExpanded(true);
		return hrOrganizationTree;
	}

	@Override
	public List<BindUserDTO> getHrUsers(TreeNode hrOrganizationTreeNode) {
		HrOrganizationDTO data = (HrOrganizationDTO) hrOrganizationTreeNode
				.getData();
		return Lists.newArrayList(jappCommonSoapClient
				.findHrUsersByHrOrganization(data.getId()));
	}

	@Override
	public String getTheme() {
		Operator operator;
		try {
			operator = getCurrentOperator();
		} catch (Exception e) {
			return super.getTheme();
		}
		if (operator == null)
			return super.getTheme();
		else
			return operator.getTheme();
	}
}
