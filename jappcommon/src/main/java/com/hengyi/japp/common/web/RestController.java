package com.hengyi.japp.common.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.bind.BindCorporation;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.dto.BindCorporationDTO;
import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.common.dto.CorporationDTO;
import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.dto.LoginUserDTO;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.ws.SoapService;

@Controller
@RequestMapping(value = "/ws/rest")
public class RestController extends AbstractController implements SoapService {
	private static final long serialVersionUID = -7697632853731195913L;

	@RequestMapping(value = "/typeahead/bindCorporation/{bindCorporationType}/{query}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody
	List<BindCorporationDTO> typeHeadBindCorporation(
			@PathVariable("bindCorporationType") final BindCorporationType bindCorporationType,
			@PathVariable("query") final String query) throws Exception {
		List<BindCorporationDTO> result = Lists.newArrayList();
		for (BindCorporation corp : corporationService
				.queryAllBindCorporationByName("*" + query + "*")) {
			result.add(dozer.map(corp, BindCorporationDTO.class));
		}
		return result;
	}

	@RequestMapping(value = "/typeahead/bindUser/{principalType}/{query}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody
	List<BindUserDTO> typeHeadBindUser(
			@PathVariable("principalType") final PrincipalType principalType,
			@PathVariable("query") final String query) throws Exception {
		List<BindUserDTO> result = Lists.newArrayList();
		for (BindUser user : userService.findAllBindUserByQuery("*" + query
				+ "*")) {
			result.add(dozer.map(user, BindUserDTO.class));
		}
		return result;
	}

	@RequestMapping(value = "/findOneUser/{principalType}/{principal}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	UserDTO findOneUser(
			@PathVariable("principalType") final PrincipalType principalType,
			@PathVariable("principal") final String principal) throws Exception {
		return soapService.findOneUser(principalType, principal);
	}

	@RequestMapping(value = "/findAllCorporation/{uuid}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	List<CorporationDTO> findAllCorporation(@PathVariable("uuid") String uuid)
			throws Exception {
		return soapService.findAllCorporation(uuid);
	}

	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	LoginUserDTO login(@PathVariable("username") final String username,
			@PathVariable("password") final String password) throws Exception {
		return soapService.login(username, password);
	}

	@RequestMapping(value = "/bindUsers/{uuid}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	List<BindUserDTO> findAllBindUser(@PathVariable("uuid") final String uuid)
			throws Exception {
		return soapService.findAllBindUser(uuid);
	}

	@RequestMapping(value = "/hrOrganizations", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	List<HrOrganizationDTO> findAllHrOrganization() throws Exception {
		return soapService.findAllHrOrganization();
	}

	@RequestMapping(value = "/hrOrganizations/{orgId}/users", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	List<BindUserDTO> findHrUsersByHrOrganization(
			@PathVariable("orgId") final String orgId) {
		return soapService.findHrUsersByHrOrganization(orgId);
	}

	@RequestMapping(value = "/calString/{cal}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	Double calString(@PathVariable("cal") final String cal) throws Exception {
		return soapService.calString(cal);
	}

	@RequestMapping(value = "/findAllUserByQuery/{nameSearch}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	List<UserDTO> findAllUserByQuery(
			@PathVariable("nameSearch") final String nameSearch)
			throws Exception {
		return soapService.findAllUserByQuery(nameSearch);
	}

	@RequestMapping(value = "/findAllUserByQuery/{nameSearch}/{size}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Override
	public @ResponseBody
	List<UserDTO> findAllUserByQuery_Size(
			@PathVariable("nameSearch") final String nameSearch,
			@PathVariable("size") final int size) throws Exception {
		return soapService.findAllUserByQuery_Size(nameSearch, size);
	}
}
