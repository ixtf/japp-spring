package com.hengyi.japp.common.domain.node;

import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.Constant;
import com.hengyi.japp.common.MyUtil;
import com.hengyi.japp.common.domain.node.bind.BindCorporation;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.common.domain.shared.Entity;
import com.sun.istack.NotNull;

@NodeEntity
public class Corporation extends AbstractNeo4j implements Entity<Corporation> {
	public static final String nameSearch = "corporationNameSearch";
	@Indexed(unique = true)
	private String uuid;
	@NotNull
	@NotEmpty
	private String shortName;
	@NotNull
	@NotEmpty
	@Indexed(indexType = IndexType.FULLTEXT, indexName = nameSearch)
	private String fullName;
	@RelatedTo(type = Constant.Relationship.BIND_CORPORATION, direction = Direction.INCOMING)
	private Set<BindCorporation> bindCorporations;

	public Corporation() {
		super();
		this.uuid = MyUtil.newUuid();
	}

	public Corporation(String name) {
		this();
		this.shortName = this.fullName = MyUtil.trimUpper(name);
	}

	public Corporation(String shortName, String fullName) {
		this();
		this.shortName = MyUtil.trimUpper(shortName);
		this.fullName = MyUtil.trimUpper(fullName);
	}

	public Set<BindCorporation> getBindCorporations() {
		if (bindCorporations == null)
			bindCorporations = Sets.newHashSet();
		return bindCorporations;
	}

	public void setBindCorporations(Set<BindCorporation> bindCorporations) {
		this.bindCorporations = bindCorporations;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return shortName;
	}

	public String getShortName() {
		return shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public boolean sameIdentityAs(Corporation other) {
		return other != null && getUuid().equals(other.getUuid());
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final Corporation other = (Corporation) object;
		return sameIdentityAs(other);
	}

	@Override
	public int hashCode() {
		return getUuid().hashCode();
	}

	public int getBindCorporationSize() {
		return getBindCorporations().size();
	}

}
