package com.hengyi.japp.common.domain.node.bind;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.domain.node.Corporation;
import com.hengyi.japp.common.domain.node.bind.corporation.HrCorporation;
import com.hengyi.japp.common.domain.node.bind.corporation.Oa1Corporation;
import com.hengyi.japp.common.domain.node.bind.corporation.Oa2Corporation;
import com.hengyi.japp.common.domain.node.bind.corporation.SapCorporation;

@NodeEntity
public interface BindCorporation {
	public static final String nameSearch = "bindCorporationNameSearch";

	Long getNodeId();

	String getId();

	String getName();

	BindCorporationType getBindCorporationType();

	Corporation getCorporation();

	public static class Builder {
		private BindCorporationType bindCorporationType;
		private String id;
		private String name;

		@SuppressWarnings("unchecked")
		public <T extends BindCorporation> T build() {
			switch (bindCorporationType) {
			case OA1:
				return (T) new Oa1Corporation(id, name);
			case OA2:
				return (T) new Oa2Corporation(id, name);
			case HR:
				return (T) new HrCorporation(id, name);
			case SAP:
				return (T) new SapCorporation(id, name);
			default:
				return null;
			}
		}

		public Builder bindCorporationType(
				BindCorporationType bindCorporationType) {
			this.bindCorporationType = bindCorporationType;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}
	}
}
