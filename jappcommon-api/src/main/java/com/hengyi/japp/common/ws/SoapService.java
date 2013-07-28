package com.hengyi.japp.common.ws;

import java.io.Serializable;
import java.util.Collection;

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

	Collection<BindUserDTO> findBindUser(final String uuid) throws Exception;

	Collection<CorporationDTO> findAllCorporation(final String uuid)
			throws Exception;

	Collection<HrOrganizationDTO> findAllHrOrganization() throws Exception;

	Double calString(final String cal) throws Exception;

	Collection<BindUserDTO> findHrUsersByHrOrganization(String orgId);
}
