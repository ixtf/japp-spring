package com.hengyi.japp.common.service.impl;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.Constant;
import com.hengyi.japp.common.MyUtil;
import com.hengyi.japp.common.command.UserBindCommand;
import com.hengyi.japp.common.command.UserSearchCommand;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.domain.node.bind.user.AbstractBindUser;
import com.hengyi.japp.common.domain.node.bind.user.InnerUser;
import com.hengyi.japp.common.domain.repository.UserRepository;
import com.hengyi.japp.common.domain.repository.bind.BindUserRepository;
import com.hengyi.japp.common.domain.repository.bind.InnerUserRepository;
import com.hengyi.japp.common.domain.repository.bind.SsoUserRepository;
import com.hengyi.japp.common.event.EventPublisher;
import com.hengyi.japp.common.service.CorporationService;
import com.hengyi.japp.common.service.UserService;

@Named
public class UserServiceImpl implements UserService {
	@Resource
	private Neo4jOperations template;
	@Resource
	private UserRepository userRepository;
	@Resource
	private BindUserRepository bindUserRepository;
	@Resource
	private InnerUserRepository innerUserRepository;
	@Resource
	private SsoUserRepository ssoUserRepository;
	@Resource
	private CorporationService corporationService;
	@Resource(name = "hrJdbcTemplate")
	private JdbcTemplate hrJdbcTemplate;
	@Resource(name = "oa1JdbcTemplate")
	private JdbcTemplate oa1JdbcTemplate;
	@Resource(name = "oa2JdbcTemplate")
	private JdbcTemplate oa2JdbcTemplate;
	@Inject
	private EventPublisher eventPublisher;

	@Override
	public User save(User user) {
		User oldUser = findOne(user.getUuid());
		if (oldUser == null)
			return userRepository.save(user);
		oldUser.setName(user.getName());
		return userRepository.save(oldUser);
	}

	@Override
	public BindUser save(BindUser user) {
		return bindUserRepository.save(user);
	}

	@Override
	public User findOne(String uuid) {
		return userRepository.findByPropertyValue(User.class.getSimpleName(),
				"uuid", uuid);
	}

	@SuppressWarnings("unchecked")
	public <T extends BindUser> T findOneBindUser(PrincipalType principalType,
			String principal) {
		BindUser bindUser = bindUserRepository.findByPropertyValue(Constant
				.getPrincipalTypeReferClass().get(principalType)
				.getSimpleName(), "principal", MyUtil.trimUpper(principal));
		return bindUser == null ? null : (T) bindUser;
	}

	@Override
	public InnerUser findOneInnerUser(String principal) {
		return innerUserRepository.findByPropertyValue(
				InnerUser.class.getSimpleName(), "principal",
				MyUtil.trimUpper(principal));
	}

	@Override
	public BindUser bindUser(UserBindCommand command) throws Exception {
		User user = findOne(command.getUuid());
		if (user == null)
			throw new Exception(CommonConstant.ErrorCode.USER_NOT_EXIST);
		AbstractBindUser bindUser = findOneBindUser(command.getPrincipalType(),
				command.getPrincipal());
		if (bindUser == null)
			bindUser = new BindUser.Builder()
					.principalType(command.getPrincipalType())
					.principal(command.getPrincipal()).name(user.getName())
					.build();
		bindUser.setUser(user);
		return save(bindUser);
	}

	@Override
	public BindUser unBindUser(UserBindCommand command) throws Exception {
		User user = findOne(command.getUuid());
		if (user == null)
			throw new Exception(CommonConstant.ErrorCode.USER_NOT_EXIST);
		BindUser bindUser = findOneBindUser(command.getPrincipalType(),
				command.getPrincipal());
		if (bindUser == null)
			throw new Exception(CommonConstant.ErrorCode.BINDUSER_NOT_EXIST);
		template.fetch(user.getBindUsers());
		user.getBindUsers().remove(bindUser);
		save(user);
		return bindUser;
	}

	@Override
	public void changePassword(String uuid, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<User> findAllByQuery(UserSearchCommand command) {
		return userRepository.findAllByQuery(User.nameSearch, "name",
				command.getName());
	}

	@Override
	public Iterable<BindUser> findAllBindUserByQuery(String nameSearch) {
		return bindUserRepository.findAllByQuery(BindUser.nameSearch, "name",
				nameSearch);
	}
}
