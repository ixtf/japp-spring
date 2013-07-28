package com.hengyi.japp.common.domain.node.bind.user;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.data.PrincipalType;

@NodeEntity
public class MobileUser extends InnerUser {
	public MobileUser() {
		super();
	}

	public MobileUser(String principal, String name) {
		super(principal, name);
	}

	@Override
	public PrincipalType getPrincipalType() {
		return PrincipalType.MOBILE;
	}
}
