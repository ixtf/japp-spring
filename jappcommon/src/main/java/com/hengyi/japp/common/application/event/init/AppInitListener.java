package com.hengyi.japp.common.application.event.init;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hengyi.japp.common.application.event.SyncEventPublisher;
import com.hengyi.japp.common.application.event.corporation.CorporationEvent;
import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.Corporation;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.BindCorporation;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.domain.node.bind.corporation.AbstractBindCorporation;
import com.hengyi.japp.common.domain.node.bind.corporation.HrCorporation;
import com.hengyi.japp.common.domain.node.bind.user.AbstractBindUser;
import com.hengyi.japp.common.domain.node.bind.user.HrUser;
import com.hengyi.japp.common.domain.repository.CorporationRepository;
import com.hengyi.japp.common.domain.repository.UserRepository;
import com.hengyi.japp.common.domain.repository.bind.BindCorporationRepository;
import com.hengyi.japp.common.domain.repository.bind.BindUserRepository;
import com.hengyi.japp.common.service.CorporationService;
import com.hengyi.japp.common.service.UserService;
import com.hengyi.japp.common.util.MyUtil;

@SuppressWarnings("unused")
@Transactional
@Component
public class AppInitListener implements ApplicationListener<AppInitEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
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
	@Resource
	private UserRepository userRepository;
	@Resource
	private BindUserRepository bindUserRepository;
	@Resource
	private CorporationRepository corporationRepository;
	@Resource
	private BindCorporationRepository bindCorporationRepository;
	@Resource
	private SyncEventPublisher publisher;

	@Override
	public void onApplicationEvent(AppInitEvent event) {
		long startTime = System.currentTimeMillis();
		log.info("开始初始化");

		if (userService.findOneBindUser(PrincipalType.HR, "12000077") == null) {
			Map<String, Corporation> corporationMap = importHrCorporation();
			importOaCorporation(BindCorporationType.OA1, oa1JdbcTemplate);
			importOaCorporation(BindCorporationType.OA2, oa2JdbcTemplate);
			publisher.publish(new CorporationEvent(this,
					CorporationEvent.Type.OA_CORPORATION_IMPORTED));

			Map<String, User> userMap = importHrUser();
			imortOaUser(PrincipalType.OA1, oa1JdbcTemplate, userMap);
			imortOaUser(PrincipalType.OA2, oa2JdbcTemplate, userMap);
		}

		long endTime = System.currentTimeMillis();
		log.info("初始化结束，所花时间：{}", endTime - startTime);
	}

	private Map<String, Corporation> importHrCorporation() {
		ImmutableMap.Builder<String, Corporation> builder = ImmutableMap
				.builder();
		Map<String, HrCorporation> map = Maps.newHashMap();
		log.info("开始导入HR公司！");
		List<Corporation> corporations = Lists.newArrayList();
		List<BindCorporation> bindCorporations = Lists.newArrayList();
		String sql = "SELECT * from DEPARTMENT_INFO WHERE COMPANY_LEVEL_CODE is not null AND to_char(cur_date,'yyyy-MM-dd')=? order by DEPARTMENT_CODE";
		SqlRowSet rs = hrJdbcTemplate.queryForRowSet(sql, new DateTime()
				.plusDays(-1).toString("yyyy-MM-dd"));
		while (rs.next()) {
			String id = MyUtil.trimUpper(rs.getString("DEPARTMENT_CODE"));
			String name = MyUtil.trimUpper(rs.getString("DEPARTMENT_NAME"));
			String parentId = MyUtil.trimUpper(rs.getString("PARENT_DEP_CODE"));
			HrCorporation hrCorporation = new HrCorporation(id, name);
			if (!parentId.equals("00000000"))
				hrCorporation.setParentHrCorporation(map.get(parentId));

			Corporation corporation = new Corporation(name);
			corporations.add(corporation);
			hrCorporation.setCorporation(corporation);
			bindCorporations.add(hrCorporation);

			map.put(hrCorporation.getId(), hrCorporation);
			builder.put(hrCorporation.getId(), corporation);
		}
		corporationRepository.save(corporations);
		bindCorporationRepository.save(bindCorporations);
		log.info("HR公司导入完毕！");
		return builder.build();
	}

	private void importOaCorporation(BindCorporationType bindCorporationType,
			JdbcTemplate jdbcTemplate) {
		log.info("开始导入{}公司！", bindCorporationType);
		List<BindCorporation> corporations = Lists.newArrayList();
		String sql = "select * from hrmsubcompany";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while (rs.next()) {
			AbstractBindCorporation oaCorporation = new BindCorporation.Builder()
					.bindCorporationType(bindCorporationType)
					.id(rs.getString("id"))
					.name(rs.getString("subcompanyname")).build();
			corporations.add(oaCorporation);
		}
		bindCorporationRepository.save(corporations);
		log.info("{}公司导入完毕！", bindCorporationType);
	}

	private Map<String, User> importHrUser() {
		ImmutableMap.Builder<String, User> builder = ImmutableMap.builder();
		log.info("开始导入Hr用户");
		List<User> users = Lists.newArrayList();
		List<BindUser> bindUsers = Lists.newArrayList();
		String sql = "SELECT * FROM HR_USERS WHERE is_enabled='1'";
		SqlRowSet rs = hrJdbcTemplate.queryForRowSet(sql);
		while (rs.next()) {
			User user = new User(rs.getString("emp_name"));
			users.add(user);

			HrUser hrUser = new HrUser(rs.getString("emp_sn"),
					rs.getString("emp_name"));
			hrUser.setUser(user);
			bindUsers.add(hrUser);

			builder.put(hrUser.getPrincipal(), user);
		}
		userRepository.save(users);
		bindUserRepository.save(bindUsers);
		log.info("Hr用户导入完毕！");
		return builder.build();
	}

	private void imortOaUser(PrincipalType principalType,
			JdbcTemplate jdbcTemplate, Map<String, User> map) {
		log.info("开始导入{}用户！", principalType);
		List<BindUser> bindUsers = Lists.newArrayList();
		String sql = "SELECT * FROM hrmresource where loginid <> '' and isnull(loginid,'') <> ''";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		while (rs.next()) {
			AbstractBindUser oaUser = new BindUser.Builder()
					.principalType(principalType)
					.principal(rs.getString("loginid"))
					.name(rs.getString("lastname")).build();
			oaUser.setUser(map.get(rs.getString("workcode")));
			bindUsers.add(oaUser);
		}
		bindUserRepository.save(bindUsers);
		log.info("{}用户导入完毕！", principalType);
	}
}
