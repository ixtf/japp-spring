package com.hengyi.japp.common.domain.node.bind.user;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.data.PrincipalType;

@NodeEntity
public class Oa2User extends SsoUser {
	public Oa2User() {
		super();
	}

	public Oa2User(String principal, String name) {
		super(principal, name);
	}

	@Override
	public PrincipalType getPrincipalType() {
		return PrincipalType.OA2;
	}
}
