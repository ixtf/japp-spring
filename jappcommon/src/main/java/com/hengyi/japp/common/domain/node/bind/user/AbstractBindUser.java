package com.hengyi.japp.common.domain.node.bind.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import com.hengyi.japp.common.application.Constant;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.common.domain.shared.Entity;
import com.hengyi.japp.common.util.MyUtil;
import com.sun.istack.NotNull;

@NodeEntity
public abstract class AbstractBindUser extends AbstractNeo4j implements
		BindUser, Entity<AbstractBindUser> {
	@NotNull
	@NotEmpty
	@Indexed(indexType = IndexType.FULLTEXT, indexName = BindUser.nameSearch)
	protected String name;
	@RelatedTo(type = Constant.Relationship.BIND_USER, direction = Direction.OUTGOING)
	@Fetch
	protected User user;

	public AbstractBindUser() {
		super();
	}

	public AbstractBindUser(String principal, String name) {
		this();
		setPrincipal(MyUtil.trimUpper(principal));
		this.name = MyUtil.trimUpper(name);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void setPrincipal(String principal);

	@Override
	public boolean sameIdentityAs(AbstractBindUser other) {
		return other != null
				&& getPrincipal() != null
				&& new EqualsBuilder()
						.append(getPrincipalType(), other.getPrincipalType())
						.append(getPrincipal(), other.getPrincipal())
						.isEquals();
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final AbstractBindUser other = (AbstractBindUser) object;
		return sameIdentityAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getPrincipalType())
				.append(getPrincipal()).toHashCode();
	}
}
