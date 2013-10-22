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

	User findOne(String uuid);

	Iterable<User> findAllByQuery(UserSearchCommand command);

	BindUser save(BindUser user);

	<T extends BindUser> T findOneBindUser(PrincipalType principalType,
			String principal);

	InnerUser findOneInnerUser(String principal);

	BindUser bindUser(UserBindCommand command) throws Exception;

	BindUser unBindUser(UserBindCommand command) throws Exception;

	void changePassword(String uuid, String password);

	Iterable<BindUser> findAllBindUserByQuery(String nameSearch);
}
