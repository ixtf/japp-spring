package com.hengyi.japp.common.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.CommonMessageUtil;
import com.hengyi.japp.common.data.PrincipalType;

public class CommonAbstractController {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	public List<PrincipalType> getAllPrincipalTypes() {
		return Lists.newArrayList(PrincipalType.values());
	}

	public int getPageSize() {
		return CommonConstant.PAGE_SIZE;
	}

	protected void redirect(String url) {
		StringBuilder sb;
		try {
			if (url.indexOf("http://") == 0)
				sb = new StringBuilder(url);
			else {
				sb = new StringBuilder("/report");
				if (url.substring(0, 1).equals("/"))
					sb.append(url);
				else
					sb.append("/").append(url);
			}
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(sb.toString());
		} catch (Exception e) {
			log.error("", e);
			errorMessage(e);
		}
	}

	protected void operationSuccessMessage() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, CommonMessageUtil
						.operationSuccess(), null));
	}

	protected void infoMessage(String s) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, CommonMessageUtil
						.info(), s));
	}

	protected void errorMessage(Exception e) {
		errorMessage(e, true);
	}

	protected void errorMessage(Exception e, Boolean toLog) {
		if (toLog)
			log.error("", e);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, CommonMessageUtil
						.operationFailure(), e.getLocalizedMessage()));
	}

	protected HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	protected String getParameter(String p) {
		return getHttpServletRequest().getParameter(p);
	}
}
