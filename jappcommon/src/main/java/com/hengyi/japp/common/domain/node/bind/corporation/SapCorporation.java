package com.hengyi.japp.common.domain.node.bind.corporation;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.data.BindCorporationType;
import com.sun.istack.NotNull;

@NodeEntity
public class SapCorporation extends AbstractBindCorporation {
	@NotNull
	@NotEmpty
	@Indexed(unique = true)
	protected String id;

	public SapCorporation() {
		super();
	}

	public SapCorporation(String id, String name) {
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

	@Override
	public BindCorporationType getBindCorporationType() {
		return BindCorporationType.SAP;
	}
}
