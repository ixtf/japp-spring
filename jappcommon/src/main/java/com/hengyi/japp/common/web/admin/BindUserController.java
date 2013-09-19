package com.hengyi.japp.common.web.admin;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableSet;
import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.Constant.AdminWeb;
import com.hengyi.japp.common.command.UserBindCommand;
import com.hengyi.japp.common.command.UserSearchCommand;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.web.AbstractController;

@Controller
public class BindUserController extends AbstractController {
	private static final int PAGE_SIZE = 10;

	@RequestMapping(value = AdminWeb.bindUserList, method = RequestMethod.GET)
	public ModelAndView userList() throws Exception {
		return userList(0);
	}

	@RequestMapping(value = AdminWeb.bindUserList + "/page/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView userList(@PathVariable("pageNumber") Integer pageNumber)
			throws Exception {
		ImmutableSet.Builder<User> users = ImmutableSet.builder();
		for (User user : userRepository.findAll(new PageRequest(pageNumber,
				PAGE_SIZE))) {
			template.fetch(user.getBindUsers());
			users.add(user);
		}
		return new ModelAndView(AdminWeb.bindUserListView)
				.addObject("users", users.build())
				.addObject("pageNumber", pageNumber)
				.addObject("command", new UserSearchCommand());
	}

	@RequestMapping(value = AdminWeb.bindUserSearch, method = RequestMethod.POST)
	public ModelAndView userSearch(
			@ModelAttribute("command") UserSearchCommand command)
			throws Exception {
		if (StringUtils.isEmpty(command.getName()))
			return userList(0);
		ImmutableSet.Builder<User> users = ImmutableSet.builder();
		for (User user : userService.queryAll(command)) {
			template.fetch(user.getBindUsers());
			users.add(user);
		}
		return new ModelAndView(AdminWeb.bindUserListView).addObject("users",
				users.build()).addObject("command", command);
	}

	@RequestMapping(value = AdminWeb.bindUserUpdate, method = RequestMethod.GET)
	public ModelAndView userUpdate() throws Exception {
		return new ModelAndView(AdminWeb.bindUserUpdateView).addObject(
				"command", new User());
	}

	@RequestMapping(value = AdminWeb.bindUserList + "/{uuid}", method = RequestMethod.GET)
	public ModelAndView userUpdate(@PathVariable("uuid") String uuid)
			throws Exception {
		User user = userService.findOne(uuid);
		if (user == null)
			throw new Exception(CommonConstant.ErrorCode.USER_NOT_EXIST + uuid);
		return new ModelAndView(AdminWeb.userUpdateView).addObject("command",
				user);
	}

	@RequestMapping(value = { AdminWeb.bindUserUpdate,
			AdminWeb.bindUserList + "/{uuid}" }, method = RequestMethod.POST)
	public ModelAndView userUpdate(
			@Valid @ModelAttribute("command") User command, BindingResult result)
			throws Exception {
		if (result.hasErrors())
			return new ModelAndView(AdminWeb.userUpdateView);
		return new ModelAndView(AdminWeb.userUpdateView).addObject("command",
				userService.save(command));
	}

	@RequestMapping(value = AdminWeb.bindUserBind + "/{uuid}", method = RequestMethod.GET)
	public ModelAndView userBind(@PathVariable("uuid") String uuid)
			throws Exception {
		User user = userService.findOne(uuid);
		template.fetch(user.getBindUsers());
		return new ModelAndView(AdminWeb.userBindView).addObject("user", user)
				.addObject("command",
						new UserBindCommand(user.getUuid(), user.getName()));
	}

	@RequestMapping(value = { AdminWeb.bindUserBind,
			AdminWeb.bindUserBind + "/{uuid}" }, method = RequestMethod.POST)
	public ModelAndView userBind(
			@Valid @ModelAttribute("command") UserBindCommand command,
			BindingResult result) throws Exception {
		if (result.hasErrors())
			return new ModelAndView(AdminWeb.userBindView);
		userService.bindUser(command);
		return new ModelAndView("redirect:" + AdminWeb.userBind + "/"
				+ command.getUuid());
	}
}
