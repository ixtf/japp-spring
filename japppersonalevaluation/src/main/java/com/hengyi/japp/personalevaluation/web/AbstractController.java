package com.hengyi.japp.personalevaluation.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.primefaces.model.TreeNode;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.personalevaluation.Constant;
import com.hengyi.japp.personalevaluation.context.ContextFactory;
import com.hengyi.japp.personalevaluation.domain.data.CompareType;
import com.hengyi.japp.personalevaluation.domain.data.Level;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.repository.TaskConfigRepository;
import com.hengyi.japp.personalevaluation.domain.repository.TaskRepository;
import com.hengyi.japp.personalevaluation.service.CacheService;
import com.hengyi.japp.personalevaluation.service.EvaluationService;
import com.hengyi.japp.personalevaluation.service.OperatorService;
import com.hengyi.japp.personalevaluation.service.TaskService;

public abstract class AbstractController implements Serializable {
	private static final long serialVersionUID = 4439434353140699253L;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	protected Neo4jOperations template;
	@Inject
	protected Mapper dozer;
	@Inject
	protected ContextFactory contextFactory;
	@Inject
	protected CacheService cacheService;
	@Inject
	protected OperatorService operatorService;
	@Inject
	protected TaskService taskService;
	@Inject
	protected EvaluationService evaluationService;
	@Inject
	protected TaskRepository taskRepository;
	@Inject
	protected TaskConfigRepository taskConfigRepository;

	protected void addInfoMessage(String s) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, s, "Success!"));
	}

	protected void addErrorMessage(Exception e) {
		ResourceBundle msg = ResourceBundle.getBundle("messages", FacesContext
				.getCurrentInstance().getViewRoot().getLocale());
		String errorMessage = getRootErrorMessage(e);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
						"Save Unsuccessful"));
	}

	protected void redirect(String url) {
		String prefix = "/personalevaluation";
		try {
			if (url.indexOf("http") >= 0)
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(url);

			if (!url.substring(0, 1).equals("/"))
				prefix = prefix + "/";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(prefix + url);
		} catch (IOException e) {
			addErrorMessage(e);
		}
	}

	protected void push(String s) {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		try {
			pushContext
					.push("/" + getCurrentOperator().getUuid(),
							new FacesMessage(FacesMessage.SEVERITY_INFO, s,
									"Success!"));
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	protected void push(FacesMessage facesMessage) throws Exception {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		pushContext.push("/" + getCurrentOperator().getUuid(), facesMessage);
	}

	protected String getRootErrorMessage(Exception e) {
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			return errorMessage;
		}

		Throwable t = e;
		while (t != null) {
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		return errorMessage;
	}

	public List<Integer> getYears() {
		ImmutableList.Builder<Integer> builder = ImmutableList.builder();
		for (int i = Constant._YEAR; i <= new DateTime().getYear(); i++)
			builder.add(i);
		return builder.build();
	}

	public Operator getCurrentOperator() throws Exception {
		return cacheService.getCurrentOperator();
	}

	public Task getCurrentTask() throws Exception {
		return cacheService.getCurrentTask();
	}

	public Operator getOperator(String empSn) throws Exception {
		return operatorService.findByEmpsn(empSn);
	}

	public TreeNode getHrOrganizationTree() throws Exception {
		return cacheService.getHrOrganizationTree();
	}

	public List<BindUserDTO> getHrOrganizationUsers() {
		List<BindUserDTO> list = Lists.newArrayList();
		if (hrOrganizationTreeNode != null)
			list = cacheService.getHrUsers(hrOrganizationTreeNode);
		return list;
	}

	// 选中的HR公司
	private TreeNode hrOrganizationTreeNode;

	public TreeNode getHrOrganizationTreeNode() {
		return hrOrganizationTreeNode;
	}

	public void setHrOrganizationTreeNode(TreeNode hrOrganizationTreeNode) {
		this.hrOrganizationTreeNode = hrOrganizationTreeNode;
	}

	public CompareType[] getCompareTypes() {
		return CompareType.values();
	}

	public Level[] getLevels() {
		return Level.values();
	}
}
