package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;

import org.primefaces.model.MenuModel;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.domain.repository.ReportRepository;

@Named
@Scope("session")
public class MenuBarController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 3666412549440666713L;
	@Resource
	private ReportRepository reportRepository;
	private MenuModel menuBar;

	@PostConstruct
	private void init() {
		menuBar = cacheService.getMenuBar();
	}

	public MenuModel getMenuBar() {
		return menuBar;
	}
}
