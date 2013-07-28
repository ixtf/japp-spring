package com.hengyi.japp.common.web.admin;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableSet;
import com.hengyi.japp.common.application.Constant;
import com.hengyi.japp.common.application.Constant.AdminWeb;
import com.hengyi.japp.common.command.CorporationBindCommand;
import com.hengyi.japp.common.domain.node.Corporation;
import com.hengyi.japp.common.domain.node.bind.BindCorporation;
import com.hengyi.japp.common.web.AbstractController;

@Controller
public class CorporationController extends AbstractController {
	@RequestMapping(value = AdminWeb.corporationList, method = RequestMethod.GET)
	public ModelAndView corporationList() throws Exception {
		ImmutableSet.Builder<Corporation> corporations = ImmutableSet.builder();
		for (Corporation corporation : corporationRepository.findAll()) {
			template.fetch(corporation.getBindCorporations());
			corporations.add(corporation);
		}
		return new ModelAndView(AdminWeb.corporationListView).addObject(
				"corporations", corporations.build());
	}

	@RequestMapping(value = AdminWeb.corporationUpdate, method = RequestMethod.GET)
	public ModelAndView corporationUpdate() throws Exception {
		return new ModelAndView(AdminWeb.corporationUpdateView).addObject(
				"command", new Corporation());
	}

	@RequestMapping(value = AdminWeb.corporationList + "/{uuid}", method = RequestMethod.GET)
	public ModelAndView corporationUpdate(@PathVariable("uuid") String uuid)
			throws Exception {
		Corporation corporation = corporationService.findOne(uuid);
		if (corporation == null)
			throw new Exception(Constant.ErrorCode.CORPORATION_NOT_EXIST + uuid);
		return new ModelAndView(AdminWeb.corporationUpdateView).addObject(
				"command", corporation);
	}

	@RequestMapping(value = AdminWeb.corporationBind + "/{uuid}", method = RequestMethod.GET)
	public ModelAndView corporationBind(@PathVariable("uuid") String uuid)
			throws Exception {
		Corporation corporation = corporationService.findOne(uuid);
		if (corporation == null)
			throw new Exception(Constant.ErrorCode.CORPORATION_NOT_EXIST + uuid);
		template.fetch(corporation.getBindCorporations());
		return new ModelAndView(AdminWeb.corporationBindView).addObject(
				"corporation", corporation).addObject("command",
				new CorporationBindCommand(uuid, corporation.getName()));
	}

	@RequestMapping(value = { AdminWeb.corporationUpdate,
			AdminWeb.corporationList + "/{uuid}" }, method = RequestMethod.POST)
	public ModelAndView corporationUpdate(
			@Valid @ModelAttribute("command") Corporation command,
			BindingResult result) throws Exception {
		if (result.hasErrors())
			return new ModelAndView(AdminWeb.corporationUpdateView);
		Corporation corporation = corporationService.save(command);
		return new ModelAndView("redirect:" + AdminWeb.corporationBind + "/"
				+ corporation.getUuid());
	}

	@RequestMapping(value = AdminWeb.corporationBind + "/{uuid}", method = RequestMethod.POST)
	public ModelAndView corporationBind(
			@Valid @ModelAttribute("command") CorporationBindCommand command,
			BindingResult result) throws Exception {
		if (result.hasErrors())
			return new ModelAndView(AdminWeb.corporationBindView);
		BindCorporation bindCorporation = corporationService
				.bindCorporation(command);
		return new ModelAndView("redirect:" + AdminWeb.corporationBind + "/"
				+ bindCorporation.getCorporation().getUuid());
	}

}
