package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.MenuModel;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class SessionController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 3666412549440666713L;
	private MenuModel menuBar;

	@PostConstruct
	private void init() throws Exception {
		menuBar = menuService.getMenuBar();
	}

	public MenuModel getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuModel menuBar) {
		this.menuBar = menuBar;
	}
}
