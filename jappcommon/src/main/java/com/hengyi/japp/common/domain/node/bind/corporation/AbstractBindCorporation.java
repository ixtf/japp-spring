package com.hengyi.japp.common.domain.node.bind.corporation;

import javax.validation.constraints.NotNull;

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
import com.hengyi.japp.common.domain.node.Corporation;
import com.hengyi.japp.common.domain.node.bind.BindCorporation;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.common.domain.shared.Entity;
import com.hengyi.japp.common.util.MyUtil;

@NodeEntity
public abstract class AbstractBindCorporation extends AbstractNeo4j implements
		BindCorporation, Entity<AbstractBindCorporation> {
	@NotNull
	@NotEmpty
	@Indexed(indexType = IndexType.FULLTEXT, indexName = BindCorporation.nameSearch)
	protected String name;
	@RelatedTo(type = Constant.Relationship.BIND_CORPORATION, direction = Direction.OUTGOING)
	@Fetch
	protected Corporation corporation;

	public AbstractBindCorporation() {
		super();
	}

	public AbstractBindCorporation(String id, String name) {
		super();
		setId(MyUtil.trimUpper(id));
		this.name = name;
	}

	public abstract void setId(String id);

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean sameIdentityAs(AbstractBindCorporation other) {
		return other != null
				&& getId() != null
				&& new EqualsBuilder()
						.append(getBindCorporationType(),
								other.getBindCorporationType())
						.append(getId(), other.getId()).isEquals();
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final AbstractBindCorporation other = (AbstractBindCorporation) object;
		return sameIdentityAs(other);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getBindCorporationType())
				.append(getId()).toHashCode();
	}
}
