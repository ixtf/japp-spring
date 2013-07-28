package com.hengyi.japp.foreign.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hengyi.japp.foreign.application.event.EventPublisher;
import com.hengyi.japp.foreign.application.event.SyncEventPublisher;
import com.hengyi.japp.foreign.domain.repository.CreditPostRepository;
import com.hengyi.japp.foreign.domain.repository.InvoiceRepository;
import com.hengyi.japp.foreign.domain.repository.StockPrepareRepository;
import com.hengyi.japp.foreign.service.CacheService;
import com.hengyi.japp.foreign.service.CreditPostService;
import com.hengyi.japp.foreign.service.InvoiceService;
import com.hengyi.japp.foreign.service.LikpService;
import com.hengyi.japp.foreign.service.StockPrepareService;
import com.hengyi.japp.foreign.service.VbakService;

public abstract class AbstractController implements Serializable {
	private static final long serialVersionUID = 4439434353140699253L;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	protected CacheService cacheService;
	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected SyncEventPublisher syncEventPublisher;
	@Inject
	protected VbakService vbakService;
	@Inject
	protected CreditPostService creditPostService;
	@Inject
	protected StockPrepareService stockPrepareService;
	@Inject
	protected InvoiceService invoiceService;
	@Inject
	protected LikpService likpService;
	@Inject
	protected CreditPostRepository creditPostRepository;
	@Inject
	protected StockPrepareRepository stockPrepareRepository;
	@Inject
	protected InvoiceRepository invoiceRepository;
	@Inject
	protected Mapper dozer;

	protected void addInfoMessage(String s) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, s, "Success!"));
	}

	protected void addRootErrorMessage(Exception e) {
		String errorMessage = getRootErrorMessage(e);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
						"Save Unsuccessful"));
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

	// protected String getMessage(String code, Object... params) {
	// log.debug("adding error message with code: " + code + " and params: "
	// + params);
	// Locale current = LocaleContextHolder.getLocale();
	// log.debug("Current locale is " + current);
	// return messageSource.getMessage(code, params, current);
	// }
	// protected void addErrorMessage(Model model, String code, Object...
	// params) {
	// log.debug("adding error message with code: " + code + " and params: "
	// + params);
	// Locale current = LocaleContextHolder.getLocale();
	// log.debug("Current locale is " + current);
	// String localizedErrorMessage = messageSource.getMessage(code, params,
	// current);
	// log.debug("Localized message is: " + localizedErrorMessage);
	// model.addAttribute(FLASH_ERROR_MESSAGE, localizedErrorMessage);
	// }
}
