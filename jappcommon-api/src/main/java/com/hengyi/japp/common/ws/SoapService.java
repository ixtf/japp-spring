package com.hengyi.japp.common.ws;

import java.io.Serializable;
import java.util.List;

import javax.jws.WebService;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.common.dto.CorporationDTO;
import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.dto.LoginUserDTO;
import com.hengyi.japp.common.dto.UserDTO;

@WebService
public interface SoapService extends Serializable {
	LoginUserDTO login(final String username, final String password)
			throws Exception;

	UserDTO findOneUser(final PrincipalType principalType,
			final String principal) throws Exception;

	List<UserDTO> findAllUserByQuery(String nameSearch) throws Exception;

	List<UserDTO> findAllUserByQuery_Size(String nameSearch, int size)
			throws Exception;

	List<BindUserDTO> findAllBindUser(final String uuid) throws Exception;

	List<CorporationDTO> findAllCorporation(final String uuid) throws Exception;

	List<HrOrganizationDTO> findAllHrOrganization() throws Exception;

	Double calString(final String cal) throws Exception;

	List<BindUserDTO> findHrUsersByHrOrganization(String orgId);
}
