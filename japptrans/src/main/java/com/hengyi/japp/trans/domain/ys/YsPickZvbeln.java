package com.hengyi.japp.trans.domain.ys;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.hengyi.japp.common.domain.shared.AbstractUuid;

@Entity
@Table(name = "t_ys_pick_zvbeln")
public class YsPickZvbeln extends AbstractUuid implements Serializable {
	private static final long serialVersionUID = 269243465465587998L;
	@NotBlank
	private String zvbeln;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private YsPick pick;

	public YsPickZvbeln() {
		super();
	}

	public YsPickZvbeln(String zvbeln) {
		super();
		this.zvbeln = zvbeln;
	}

	public String getZvbeln() {
		return zvbeln;
	}

	public void setZvbeln(String zvbeln) {
		this.zvbeln = zvbeln;
	}

	public YsPick getPick() {
		return pick;
	}

	public void setPick(YsPick pick) {
		this.pick = pick;
	}

	@Override
	public String toString() {
		return getZvbeln();
	}
}
