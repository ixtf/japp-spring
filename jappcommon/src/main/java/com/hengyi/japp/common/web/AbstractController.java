package com.hengyi.japp.common.web;

import java.util.Locale;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.common.collect.ImmutableSet;
import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.repository.CorporationRepository;
import com.hengyi.japp.common.domain.repository.UserRepository;
import com.hengyi.japp.common.domain.repository.bind.BindCorporationRepository;
import com.hengyi.japp.common.domain.repository.bind.BindUserRepository;
import com.hengyi.japp.common.service.CorporationService;
import com.hengyi.japp.common.service.UserService;
import com.hengyi.japp.common.ws.SoapService;

public abstract class AbstractController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final String FLASH_ERROR_MESSAGE = "errorMessage";
	protected static final String redirect = "redirect:";
	@Resource(name = "jappCommonSoapClient")
	protected SoapService soapService;
	@Resource
	protected MessageSource messageSource;
	@Resource
	protected Mapper dozer;
	@Resource
	protected Neo4jOperations template;
	@Resource
	protected UserService userService;
	@Resource
	protected UserRepository userRepository;
	@Resource
	protected BindUserRepository bindUserRepository;
	@Resource
	protected CorporationService corporationService;
	@Resource
	protected CorporationRepository corporationRepository;
	@Resource
	protected BindCorporationRepository bindCorporationRepository;

	protected void addErrorMessage(Model model, String code, Object... params) {
		log.debug("adding error message with code: " + code + " and params: "
				+ params);
		Locale current = LocaleContextHolder.getLocale();
		log.debug("Current locale is " + current);
		String localizedErrorMessage = messageSource.getMessage(code, params,
				current);
		log.debug("Localized message is: " + localizedErrorMessage);
		model.addAttribute(FLASH_ERROR_MESSAGE, localizedErrorMessage);
	}

	@ModelAttribute("principalTypes")
	protected Iterable<PrincipalType> getPrincipalTypes() {
		return ImmutableSet.copyOf(PrincipalType.values());
	}

	@ModelAttribute("bindCorporationTypes")
	protected Iterable<BindCorporationType> getBindCorporationTypes() {
		return ImmutableSet.copyOf(BindCorporationType.values());
	}
}
