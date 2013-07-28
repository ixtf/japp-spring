package com.hengyi.japp.common.domain.node.bind.user;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.data.PrincipalType;

@NodeEntity
public class EmailUser extends InnerUser {
	public EmailUser() {
		super();
	}

	public EmailUser(String principal, String name) {
		super(principal, name);
	}

	@Override
	public PrincipalType getPrincipalType() {
		return PrincipalType.EMAIL;
	}
}
