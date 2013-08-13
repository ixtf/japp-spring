package com.hengyi.japp.crm.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.Mapper;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.hengyi.japp.crm.domain.node.Operator;
import com.hengyi.japp.crm.domain.node.customer.Customer;
import com.hengyi.japp.crm.service.CacheServiceFacade;
import com.hengyi.japp.crm.service.CustomerService;
import com.hengyi.japp.crm.service.OperatorService;

public abstract class AbstractController implements Serializable {
	private static final long serialVersionUID = 4439434353140699253L;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@PersistenceContext
	EntityManager em;
	@Inject
	protected Mapper dozer;
	@Inject
	protected CacheServiceFacade cacheService;
	@Inject
	protected OperatorService operatorService;
	@Inject
	protected CustomerService customerService;

	protected void addInfoMessage(String s) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, s, "Success!"));
	}

	protected void addErrorMessage(Exception e) {
		ResourceBundle msg = ResourceBundle.getBundle("messages", FacesContext
				.getCurrentInstance().getViewRoot().getLocale());
		String errorMessage = getRootErrorMessage(e);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
						"Save Unsuccessful"));
	}

	protected void redirect(String url) {
		String prefix = "/crm";
		try {
			if (url.indexOf("http") >= 0)
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(url);

			if (!url.substring(0, 1).equals("/"))
				prefix = prefix + "/";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(prefix + url);
		} catch (IOException e) {
			addErrorMessage(e);
		}
	}

	protected void push(String s) {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		try {
			pushContext
					.push("/" + getCurrentOperator().getUuid(),
							new FacesMessage(FacesMessage.SEVERITY_INFO, s,
									"Success!"));
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	protected void push(FacesMessage facesMessage) throws Exception {
		PushContext pushContext = PushContextFactory.getDefault()
				.getPushContext();
		pushContext.push("/" + getCurrentOperator().getUuid(), facesMessage);
	}

	protected String getRootErrorMessage(Exception e) {
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			return errorMessage;
		}

		Throwable t = e;
		while (t != null) {
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		return errorMessage;
	}

	public Operator getCurrentOperator() throws Exception {
		return cacheService.getCurrentOperator();
	}
}
