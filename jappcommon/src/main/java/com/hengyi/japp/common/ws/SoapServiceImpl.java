package com.hengyi.japp.common.ws;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.Corporation;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.dto.BindUserDTO;
import com.hengyi.japp.common.dto.CorporationDTO;
import com.hengyi.japp.common.dto.HrOrganizationDTO;
import com.hengyi.japp.common.dto.LoginUserDTO;
import com.hengyi.japp.common.dto.UserDTO;
import com.hengyi.japp.common.service.CorporationService;
import com.hengyi.japp.common.service.UserService;

@WebService
public class SoapServiceImpl implements SoapService {
	private static final long serialVersionUID = -6776552763480186213L;
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private Neo4jOperations template;
	@Resource
	private Mapper dozer;
	@Resource
	private UserService userService;
	@Resource
	private CorporationService corporationService;
	@Resource(name = "hrJdbcTemplate")
	private JdbcTemplate hrJdbcTemplate;

	@Override
	public LoginUserDTO login(String username, String password)
			throws Exception {
		log.info("用户：{} 开始登录！", username);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(username, password));
			for (String s : subject.getPrincipals().getRealmNames()) {
				PrincipalType principalType = PrincipalType.valueOf(s);
				BindUser bindUser = userService.findOneBindUser(principalType,
						username);
				if (bindUser == null)
					continue;
				LoginUserDTO result = dozer.map(bindUser, LoginUserDTO.class);
				dozer.map(bindUser.getUser(), result);
				return result;
			}
			log.error("用户：{} 登录成功，却找不到对应juser！");
		} catch (Exception e) {
			throw new Exception(CommonConstant.ErrorCode.USER_UNIQUE_NOT_EXIST
					+ username);
		} finally {
			subject.logout();
		}
		return null;
	}

	@Override
	public UserDTO findOneUser(PrincipalType principalType, String principal)
			throws Exception {
		BindUser bindUser = userService.findOneBindUser(principalType,
				principal);
		if (bindUser == null)
			throw new Exception(CommonConstant.ErrorCode.USER_NOT_EXIST
					+ principalType + principal);
		return dozer.map(bindUser.getUser(), UserDTO.class);
	}

	@Override
	public Collection<BindUserDTO> findBindUser(String uuid) throws Exception {
		User user = userService.findOne(uuid);
		if (user == null)
			throw new Exception(CommonConstant.ErrorCode.USER_NOT_EXIST + uuid);
		ImmutableList.Builder<BindUserDTO> builder = ImmutableList.builder();
		for (BindUser bindUser : template.fetch(user.getBindUsers()))
			builder.add(dozer.map(bindUser, BindUserDTO.class));
		return builder.build();
	}

	@Override
	public Collection<CorporationDTO> findAllCorporation(String uuid)
			throws Exception {
		User user = userService.findOne(uuid);
		if (user == null)
			throw new Exception(CommonConstant.ErrorCode.USER_NOT_EXIST + uuid);
		ImmutableList.Builder<CorporationDTO> builder = ImmutableList.builder();
		for (Corporation corporation : corporationService.findAllByUser(user)) {
			builder.add(dozer.map(corporation, CorporationDTO.class));
		}
		return builder.build();
	}

	private final String findAllHrOrganizationSql = "select DEPARTMENT_CODE as id,DEPARTMENT_NAME as name,"
			+ "PARENT_DEP_CODE as pId from DEPARTMENT_INFO "
			+ "where to_char(cur_date,'yyyy-MM-dd')=?";

	@Override
	public Collection<HrOrganizationDTO> findAllHrOrganization()
			throws Exception {
		Set<HrOrganizationDTO> result = Sets.newHashSet();
		SqlRowSet rs = hrJdbcTemplate.queryForRowSet(findAllHrOrganizationSql,
				new DateTime().plusDays(-1).toString("yyyy-MM-dd"));
		while (rs.next()) {
			HrOrganizationDTO org = new HrOrganizationDTO();
			org.setId(rs.getString(1));
			org.setName(rs.getString(2));
			org.setpId(rs.getString(3));
			result.add(org);
		}
		return result;
	}

	private final String findHrUsersByHrOrganizationSql = "select EMP_SN as username,EMP_NAME as name "
			+ "from HR_USERS where DEPARTMENT_CODE=? and IS_ENABLED='1' order by EMP_SN";

	@Override
	public Collection<BindUserDTO> findHrUsersByHrOrganization(String orgId) {
		List<BindUserDTO> result = Lists.newArrayList();
		SqlRowSet rs = hrJdbcTemplate.queryForRowSet(
				findHrUsersByHrOrganizationSql, orgId);
		while (rs.next()) {
			BindUserDTO user = new BindUserDTO();
			user.setPrincipalType(PrincipalType.HR);
			user.setPrincipal(rs.getString(1));
			user.setName(rs.getString(2));
			result.add(user);
		}
		return result;
	}

	private static final String calSql = "select %s from dual";

	@Override
	public Double calString(String cal) throws Exception {
		return hrJdbcTemplate.queryForObject(String.format(calSql, cal),
				Double.class);
	}
}
