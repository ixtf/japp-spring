package com.hengyi.japp.report.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("view")
public class HomeController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -1470555038437736246L;
	private static final String URL = "http://192.168.17.51:8075/WebReport/ReportServer?reportlet=";
	private String url;

	public String test(String s) {
		return "test-" + s;
	}

	public void report() {
		redirect("/");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = URL + url;
	}
}
