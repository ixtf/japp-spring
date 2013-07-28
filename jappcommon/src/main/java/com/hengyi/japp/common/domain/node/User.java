package com.hengyi.japp.common.domain.node;

import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.application.Constant;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.common.domain.shared.Entity;
import com.hengyi.japp.common.util.MyUtil;
import com.sun.istack.NotNull;

@NodeEntity
public class User extends AbstractNeo4j implements Entity<User> {
	public static final String nameSearch = "userNameSearch";
	@Indexed(unique = true)
	private String uuid;
	@NotNull
	@NotEmpty
	@Indexed(indexType = IndexType.FULLTEXT, indexName = nameSearch)
	private String name;
	@RelatedTo(type = Constant.Relationship.BIND_USER, direction = Direction.INCOMING)
	private Set<BindUser> bindUsers;
	@RelatedTo(type = Constant.Relationship.USER_CORPORATION, direction = Direction.OUTGOING)
	protected Set<Corporation> corporations;

	public User() {
		super();
		this.uuid = MyUtil.newUuid();
	}

	public User(String name) {
		this();
		this.name = MyUtil.trimUpper(name);
	}

	public Set<Corporation> getCorporations() {
		if (corporations == null)
			corporations = Sets.newHashSet();
		return corporations;
	}

	public void setCorporations(Set<Corporation> corporations) {
		this.corporations = corporations;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public Set<BindUser> getBindUsers() {
		if (bindUsers == null)
			bindUsers = Sets.newHashSet();
		return bindUsers;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBindUsers(Set<BindUser> bindUsers) {
		this.bindUsers = bindUsers;
	}

	@Override
	public boolean sameIdentityAs(User other) {
		return other != null && getUuid().equals(other.getUuid());
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final User other = (User) object;
		return sameIdentityAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getUuid()).toHashCode();
	}

	public int getBindUserSize() {
		return getBindUsers().size();
	}
}
