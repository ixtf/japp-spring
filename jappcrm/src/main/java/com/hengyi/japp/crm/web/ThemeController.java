package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.common.event.ThemeUpdateEvent;

@Named
@Scope("request")
public class ThemeController extends AbstractController implements Serializable {
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
			eventPublisher.publish(new ThemeUpdateEvent(theme,
					getCurrentOperator().getUuid()));
			cacheService.getCurrentOperator().setTheme(getTheme());
			infoMessage("皮肤设定成功！");
		} catch (Exception e) {
			errorMessage(e);
		}
	}
}
