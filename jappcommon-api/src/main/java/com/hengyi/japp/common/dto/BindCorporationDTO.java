package com.hengyi.japp.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@XmlRootElement(namespace = CommonConstant.NAME_SPACE, name = "BindCorporation")
public class BindCorporationDTO extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -4450533355291540767L;
	private BindCorporationType bindCorporationType;
	private String id;
	private String name;
	private CorporationDTO corporation;

	public BindCorporationType getBindCorporationType() {
		return bindCorporationType;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public CorporationDTO getCorporation() {
		return corporation;
	}

	public void setBindCorporationType(BindCorporationType bindCorporationType) {
		this.bindCorporationType = bindCorporationType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCorporation(CorporationDTO corporation) {
		this.corporation = corporation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BindCorporationDTO other = (BindCorporationDTO) o;
		return Objects.equal(getBindCorporationType(),
				other.getBindCorporationType())
				&& Objects.equal(getId(), other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getBindCorporationType(), getId());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getName()).toString();
	}
}
