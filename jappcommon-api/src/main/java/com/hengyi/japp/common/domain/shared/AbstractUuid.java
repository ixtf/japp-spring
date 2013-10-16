package com.hengyi.japp.common.domain.shared;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.google.common.base.Objects;

@MappedSuperclass
public abstract class AbstractUuid implements Serializable {
	private static final long serialVersionUID = -4199824658837211247L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	protected String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object)
			return true;
		if (object == null || getUuid() == null
				|| getClass() != object.getClass())
			return false;
		AbstractUuid other = (AbstractUuid) object;
		return Objects.equal(getUuid(), other.getUuid());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUuid());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getUuid()).toString();
	}
}
