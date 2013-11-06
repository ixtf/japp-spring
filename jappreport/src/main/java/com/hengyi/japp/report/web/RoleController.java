package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Parameter;
import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.Report;
import com.hengyi.japp.report.domain.Role;

@Named
@Scope("view")
public class RoleController extends AbstractController implements Serializable {
	private static final long serialVersionUID = 3627241517253990782L;
	@Parameter("id")
	private Long nodeId;
	private Role role;
	private List<Menu> menus;
	private List<Report> reports;
	private Menu menu;
	private Report report;

	public void save() {
		try {
			roleService.save(getRole(), getMenus(), getReports());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
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

	public Role getRole() {
		if (role != null)
			return role;
		if (getNodeId() == null)
			role = new Role();
		else
			role = roleService.findOne(nodeId);
		return role;
	}

	public List<Menu> getMenus() {
		if (menus == null)
			menus = Lists.newArrayList(getRole().getMenus(template));
		return menus;
	}

	public List<Report> getReports() {
		if (reports == null)
			reports = Lists.newArrayList(getRole().getReports(template));
		return reports;
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

	public void setRole(Role role) {
		this.role = role;
	}

	public Menu getMenu() {
		return menu;
	}

	public Report getReport() {
		return report;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setReport(Report report) {
		this.report = report;
	}
}
