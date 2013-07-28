package com.hengyi.japp.personalevaluation.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.primefaces.model.TreeNode;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.domain.data.TaskStatus;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.repository.OperatorRepository;
import com.hengyi.japp.personalevaluation.domain.repository.PersonRepository;
import com.hengyi.japp.personalevaluation.domain.repository.TaskRepository;
import com.hengyi.japp.personalevaluation.service.CacheService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.utils.MyUtil;

@SuppressWarnings("unchecked")
@Named
@Singleton
public class CacheServiceImpl implements CacheService {
	@Inject
	private OperatorService operatorService;
	@Inject
	private OperatorRepository operatorRepository;
	@Inject
	private TaskRepository taskRepository;
	@Inject
	private PersonRepository personRepository;
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;

	private TreeNode hrOrganizationTree;

	@Override
	public <T> T getSession(Object key, Class<T> value) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return (T) session.getAttribute(key);
	}

	@Override
	public void setSession(Object key, Object value) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.setAttribute(key, value);
	}

	@Override
	public Operator getCurrentOperator() throws Exception {
		Operator operator = getSession(Constant.SESSION_OPERATOR,
				Operator.class);
		if (operator != null)
			return operator;

		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated())
			return null;
		if ("admin".equals(subject.getPrincipal()))
			return new Operator(Constant.ADMIN_PRINCIPAL,
					Constant.ADMIN_PRINCIPAL);
		if (operator == null) {
			UserDTO user = getSession(
					com.hengyi.japp.common.Constant.SESSION_USER, UserDTO.class);
			operator = operatorService.findByUuid(user.getUuid());
			if (operator == null) {
				operator = new Operator(user.getUuid(), user.getName());
				operatorRepository.save(operator);
			}
			setSession(Constant.SESSION_OPERATOR, operator);
		}
		return operator;
	}

	@Override
	public Task getCurrentTask() throws Exception {
		Task task = getSession(Constant.SESSION_TASK, Task.class);
		if (task == null) {
			task = getDefaultTask();
			setSession(Constant.SESSION_TASK, task);
		}
		return task;
	}

	private Task getDefaultTask() throws Exception {
		Task result = null;
		for (Task task : taskRepository.findAllByOperator(getCurrentOperator())) {
			if (TaskStatus.ACTIVE.equals(task.getStatus()))
				return task;
			result = task;
		}
		return result;
	}

	@Override
	public TreeNode getHrOrganizationTree() throws Exception {
		return hrOrganizationTree;
	}

	@Override
	public List<BindUserDTO> getHrUsers(TreeNode hrOrganizationTreeNode) {
		HrOrganizationDTO data = (HrOrganizationDTO) hrOrganizationTreeNode
				.getData();
		return Lists.newArrayList(jappCommonSoapClient
				.findHrUsersByHrOrganization(data.getId()));
	}

	@PostConstruct
	private void init() throws Exception {
		hrOrganizationTree = MyUtil.tree(
				jappCommonSoapClient.findAllHrOrganization(), "id", "pId");
		for (TreeNode node : hrOrganizationTree.getChildren())
			node.setExpanded(true);
	}
}
