package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.Role;
import com.hengyi.japp.report.event.operator.OperatorUpdateEvent;

@Named
@Scope("view")
public class OperatorController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 3627241517253990782L;
	@Resource(name = "jappCommonSoapClient")
	private com.hengyi.japp.common.ws.SoapService jappCommonSoapClient;
	private Long nodeId;
	private Operator operator;
	private List<Role> roles;
	private List<Menu> menus;
	private List<Report> reports;
	private Role role;
	private Menu menu;
	private Report report;

	private PrincipalType principalType = PrincipalType.OA1;
	private String principal;

	public void newOperator() {
		try {
			UserDTO user = jappCommonSoapClient.findOneUser(principalType,
					principal);
			operator = operatorService.findOne(user);
			redirect(operatorService.getUpdatePath(operator.getNodeId()));
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void save() {
		try {
			operatorService.save(getOperator(), getRoles(), getMenus(),
					getReports());
			eventPublisher.publish(new OperatorUpdateEvent(getOperator()));
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void addRole() {
		if (!getRoles().contains(role))
			roles.add(role);
	}

	public void removeRole() {
		getRoles().remove(role);
	}

	public void addMenu() {
		if (!getMenus().contains(menu))
			menus.add(menu);
	}

	public void removeMenu() {
		getMenus().remove(menu);
	}

	public void addReport() {
		if (!getReports().contains(report))
			reports.add(report);
	}

	public void removeReport() {
		getReports().remove(report);
	}

	public Operator getOperator() {
		if (operator == null)
			operator = operatorService.findOne(getNodeId());
		return operator;
	}

	public List<Role> getRoles() {
		if (roles == null)
			roles = Lists.newArrayList(getOperator().getRoles(template));
		return roles;
	}

	public List<Menu> getMenus() {
		if (menus == null)
			menus = Lists.newArrayList(getOperator().getMenus(template));
		return menus;
	}

	public List<Report> getReports() {
		if (reports == null)
			reports = Lists.newArrayList(getOperator().getReports(template));
		return reports;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public PrincipalType getPrincipalType() {
		return principalType;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipalType(PrincipalType principalType) {
		this.principalType = principalType;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Role getRole() {
		return role;
	}

	public Menu getMenu() {
		return menu;
	}

	public Report getReport() {
		return report;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setReport(Report report) {
		this.report = report;
	}
}
