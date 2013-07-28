package com.hengyi.japp.common.domain.node.bind;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.data.PrincipalType;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.user.HrUser;
import com.hengyi.japp.common.domain.node.bind.user.MobileUser;
import com.hengyi.japp.common.domain.node.bind.user.Oa1User;
import com.hengyi.japp.common.domain.node.bind.user.Oa2User;
import com.hengyi.japp.common.domain.node.bind.user.SapUser;

@NodeEntity
public interface BindUser {
	public static final String nameSearch = "bindUserNameSearch";

	Long getNodeId();

	PrincipalType getPrincipalType();

	String getPrincipal();

	String getName();

	User getUser();

	public static class Builder {
		private PrincipalType principalType;
		private String principal;
		private String name;

		@SuppressWarnings("unchecked")
		public <T extends BindUser> T build() {
			switch (principalType) {
			case MOBILE:
				return (T) new MobileUser(principal, name);
			case EMAIL:
				return (T) new HrUser(principal, name);
			case SAP:
				return (T) new SapUser(principal, name);
			case OA1:
				return (T) new Oa1User(principal, name);
			case OA2:
				return (T) new Oa2User(principal, name);
			case HR:
				return (T) new HrUser(principal, name);
				// case QQ:
				// return (T) new QqUser(principal, name);
				// case SINA_WEIBO:
				// return (T) new HrUser(principal, name);
			default:
				return null;
			}
		}

		public Builder principal(String principal) {
			this.principal = principal;
			return this;
		}

		public Builder principalType(PrincipalType principalType) {
			this.principalType = principalType;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}
	}
}
