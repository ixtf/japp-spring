package com.hengyi.japp.common.service;

import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.command.UserBindCommand;
import com.hengyi.japp.common.command.UserSearchCommand;
import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.domain.node.bind.user.InnerUser;

@Transactional
public interface UserService {
	User save(User user);

	BindUser save(BindUser user);

	User findOne(String uuid);

	<T extends BindUser> T findOneBindUser(PrincipalType principalType,
			String principal);

	InnerUser findOneInnerUser(String principal);

	BindUser bindUser(UserBindCommand command) throws Exception;

	BindUser unBindUser(UserBindCommand command) throws Exception;

	Iterable<User> queryAll(UserSearchCommand command);

	void changePassword(String uuid, String password);

	Iterable<BindUser> queryAllBindUser(String string);
}
