package com.hengyi.japp.common.domain.node.bind.corporation;

import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.hengyi.japp.common.application.Constant;
import com.hengyi.japp.common.data.BindCorporationType;
import com.sun.istack.NotNull;

@NodeEntity
public class HrCorporation extends AbstractBindCorporation {
	@NotNull
	@NotEmpty
	@Indexed(unique = true)
	protected String id;
	@RelatedTo(type = Constant.Relationship.HR_CORPORATION_PARENT, direction = Direction.INCOMING)
	@Fetch
	protected HrCorporation parentHrCorporation;

	public HrCorporation() {
		super();
	}

	public HrCorporation(String id, String name) {
		super(id, name);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public HrCorporation getParentHrCorporation() {
		return parentHrCorporation;
	}

	public void setParentHrCorporation(HrCorporation parentHrCorporation) {
		this.parentHrCorporation = parentHrCorporation;
	}

	@Override
	public BindCorporationType getBindCorporationType() {
		return BindCorporationType.HR;
	}
}
