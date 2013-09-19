package com.hengyi.japp.common.event.user;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.domain.node.bind.user.AbstractBindUser;
import com.hengyi.japp.common.domain.node.bind.user.HrUser;
import com.hengyi.japp.common.service.CorporationService;
import com.hengyi.japp.common.service.UserService;

@Transactional
@Component
public class UserEventListener implements ApplicationListener<UserEvent> {
	@Resource
	private UserService userService;
	@Resource
	private CorporationService corporationService;
	@Resource(name = "hrJdbcTemplate")
	private JdbcTemplate hrJdbcTemplate;
	@Resource(name = "oa1JdbcTemplate")
	private JdbcTemplate oa1JdbcTemplate;
	@Resource(name = "oa2JdbcTemplate")
	private JdbcTemplate oa2JdbcTemplate;

	@Override
	public void onApplicationEvent(UserEvent event) {
		UserEvent.Type type = event.getType();
		switch (type) {
		case HR_USER_CREATED:
			mapOaHrUser((HrUser) event.getSource());
			break;
		default:
			break;
		}
	}

	private void mapOaHrUser(HrUser hrUser) {
		createOaUser(PrincipalType.OA1, oa1JdbcTemplate, hrUser);
		createOaUser(PrincipalType.OA2, oa2JdbcTemplate, hrUser);
	}

	private void createOaUser(PrincipalType principalType,
			JdbcTemplate jdbcTemplate, HrUser hrUser) {
		String sql = "SELECT * FROM hrmresource WHERE workcode=?";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, hrUser.getPrincipal());
		if (!rs.next())
			return;
		AbstractBindUser oaUser = new BindUser.Builder()
				.principalType(principalType)
				.principal(rs.getString("loginid"))
				.name(rs.getString("lastname")).build();
		oaUser.setUser(hrUser.getUser());
		userService.save(oaUser);
	}
}
