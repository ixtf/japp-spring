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
import com.hengyi.japp.common.command.BindCorporationUpdateCommand;
import com.hengyi.japp.common.command.CorporationBindCommand;
import com.hengyi.japp.common.domain.node.bind.BindCorporation;
import com.hengyi.japp.common.web.AbstractController;

@Controller
public class BindCorporationController extends AbstractController {
	@RequestMapping(value = AdminWeb.bindCorporationList, method = RequestMethod.GET)
	public ModelAndView bindCorporationList() throws Exception {
		ImmutableSet.Builder<BindCorporation> bindCorporations = ImmutableSet
				.builder();
		for (BindCorporation bindCorporation : bindCorporationRepository
				.findAll()) {
			bindCorporations.add(bindCorporation);
		}
		return new ModelAndView(AdminWeb.bindCorporationListView).addObject(
				"bindCorporations", bindCorporations.build());
	}

	@RequestMapping(value = AdminWeb.bindCorporationUpdate, method = RequestMethod.GET)
	public ModelAndView bindCorporationUpdate() throws Exception {
		return new ModelAndView(AdminWeb.bindCorporationUpdateView).addObject(
				"command", new BindCorporationUpdateCommand());
	}

	@RequestMapping(value = AdminWeb.bindCorporationList + "/{nodeId}", method = RequestMethod.GET)
	public ModelAndView bindCorporationUpdate(
			@PathVariable("nodeId") Long nodeId) throws Exception {
		BindCorporation bindCorporation = bindCorporationRepository
				.findOne(nodeId);
		if (bindCorporation == null)
			throw new Exception(Constant.ErrorCode.CORPORATION_NOT_EXIST
					+ nodeId);
		return new ModelAndView(AdminWeb.bindCorporationUpdateView).addObject(
				"command", bindCorporation);
	}

	@RequestMapping(value = AdminWeb.bindCorporationBind + "/{nodeId}", method = RequestMethod.GET)
	public ModelAndView bindCorporationBind(@PathVariable("nodeId") Long nodeId)
			throws Exception {
		BindCorporation bindCorporation = bindCorporationRepository
				.findOne(nodeId);
		if (bindCorporation == null)
			throw new Exception(Constant.ErrorCode.CORPORATION_NOT_EXIST
					+ nodeId);

		return new ModelAndView(AdminWeb.bindCorporationBindView).addObject(
				"bindCorporation", bindCorporation).addObject("command",
				dozer.map(bindCorporation, CorporationBindCommand.class));
	}

	@RequestMapping(value = { AdminWeb.bindCorporationUpdate,
			AdminWeb.bindCorporationList + "/{nodeId}" }, method = RequestMethod.POST)
	public ModelAndView corporationUpdate(
			@Valid @ModelAttribute("command") BindCorporationUpdateCommand command,
			BindingResult result) throws Exception {
		if (result.hasErrors())
			return new ModelAndView(AdminWeb.bindCorporationUpdateView);
		BindCorporation bindCorporation = corporationService.save(command);
		return new ModelAndView("redirect:" + AdminWeb.bindCorporationBind
				+ "/" + bindCorporation.getNodeId());
	}

	@RequestMapping(value = { AdminWeb.bindCorporationBind,
			AdminWeb.bindCorporationBind + "/{nodeId}" }, method = RequestMethod.POST)
	public ModelAndView corporationBind(
			@Valid @ModelAttribute("command") CorporationBindCommand command,
			BindingResult result) throws Exception {
		if (result.hasErrors())
			return new ModelAndView(AdminWeb.corporationBindView);
		BindCorporation bindCorporation = corporationService
				.bindCorporation(command);
		return new ModelAndView("redirect:" + AdminWeb.bindCorporationBind
				+ "/" + bindCorporation.getNodeId());
	}

}
