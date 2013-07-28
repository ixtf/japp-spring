package com.hengyi.japp.foreign.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hengyi.japp.common.domain.shared.ValueObject;
import com.hengyi.japp.common.sap.dto.LipsPK;
import com.hengyi.japp.common.sap.dto.VbapPK;

@Entity
@Table(name = "t_lips")
public class Lips implements ValueObject<Lips> {
	private static final long serialVersionUID = 2667796470379914692L;
	@EmbeddedId
	private LipsPK pk;
	@MapsId("vbeln")
	@JoinColumn(name = "vbeln")
	@ManyToOne(optional = false)
	private Likp likp;
	private String vgbel;
	private String vgpos;
	@Transient
	private VbapPK vbapPK;

	public LipsPK getPk() {
		return pk;
	}

	public void setPk(LipsPK pk) {
		this.pk = pk;
	}

	public String getVgbel() {
		return vgbel;
	}

	public String getVgpos() {
		return vgpos;
	}

	public void setVgbel(String vgbel) {
		this.vgbel = vgbel;
	}

	public void setVgpos(String vgpos) {
		this.vgpos = vgpos;
	}

	public VbapPK getVbapPK() {
		if (vbapPK == null)
			vbapPK = new VbapPK(getVgbel(), getVgpos());
		return vbapPK;
	}

	public Likp getLikp() {
		return likp;
	}

	public void setLikp(Likp likp) {
		this.likp = likp;
		likp.getLipss().add(this);
	}

	public Lips() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lips(LipsPK pk) {
		this();
		this.setPk(pk);
	}

	public Lips(String vbeln, String posnr) {
		this(new LipsPK(vbeln, posnr));
	}

	@Override
	public boolean sameValueAs(Lips other) {
		return other != null
				&& new EqualsBuilder().append(getPk(), other.getPk())
						.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getPk()).hashCode();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Lips other = (Lips) o;
		return sameValueAs(other);
	}
}
