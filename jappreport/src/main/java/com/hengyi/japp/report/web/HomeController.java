package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.event.ClearSessionReportEvent;

@Named
@Scope("request")
public class HomeController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -2095926297894443335L;

	@PostConstruct
	private void init() {
		try {
			eventBus.post(new ClearSessionReportEvent(getCurrentOperator()));
		} catch (Exception e) {
			errorMessage(e);
		}
	}
}
