package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;

import org.ocpsoft.rewrite.annotation.Parameter;
import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.report.domain.Menu;
import com.hengyi.japp.report.domain.repository.ReportRepository;

@Named
@Scope("view")
public class MenuController extends AbstractController implements Serializable {
	private static final long serialVersionUID = 3627241517253990782L;
	@Resource
	private ReportRepository reportRepository;
	@Parameter("id")
	private Long nodeId;
	private Menu menu;
	private Menu parent;
	private List<Menu> allMenus;

	@PostConstruct
	private void init() {
		allMenus = Lists.newArrayList(menuService.findAll());
	}

	public void save() {
		try {
			menuService.save(getMenu(), getParent());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public Menu getMenu() {
		if (menu != null)
			return menu;
		if (getNodeId() == null)
			menu = new Menu();
		else
			menu = menuService.findOne(getNodeId());
		return menu;
	}

	public Menu getParent() {
		if (parent == null)
			parent = getMenu().getParent();
		return parent;
	}

	public List<Menu> getAllMenus() {
		return allMenus;
	}

	public void setAllMenus(List<Menu> allMenus) {
		this.allMenus = allMenus;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
