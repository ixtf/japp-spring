package com.hengyi.japp.crm.web;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class ThemeController extends AbstractController {
	private static final long serialVersionUID = 6248447393153537183L;
	private String theme;

	public String getTheme() throws Exception {
		if (theme == null)
			theme = cacheService.getCurrentOperator().getTheme();
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void changeTheme() {
		try {
			operatorService.updateTheme(getCurrentOperator().getUuid(),
					getTheme());
			cacheService.getCurrentOperator().setTheme(getTheme());
			addInfoMessage("皮肤设定成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}
}
