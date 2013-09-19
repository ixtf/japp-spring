package com.hengyi.japp.trans.domain.ys;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.collect.Sets;
import com.hengyi.japp.trans.domain.Operator;
import com.hengyi.japp.trans.domain.SendRecord;

@Entity
@Table(name = "t_ys_delivery")
public class YsDelivery extends SendRecord {
	private static final long serialVersionUID = 7072887140116935881L;
	private boolean deleteFlag;
	private String ip;
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private YsPackType packType;
	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private YsTransType transType;
	@NotNull
	@Size(min = 1)
	@OneToMany(mappedBy = "ysDelivery", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<YsDeliveryLips> ysDeliveryLipss;
	@ManyToOne
	private Operator auditor;
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;

	public Operator getAuditor() {
		return auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditor(Operator auditor) {
		this.auditor = auditor;
		setAuditTime(new Date());
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Iterable<YsDeliveryLips> getYsDeliveryLipss() {
		return ysDeliveryLipss;
	}

	public YsPackType getPackType() {
		return packType;
	}

	public YsTransType getTransType() {
		return transType;
	}

	public String getIp() {
		return ip;
	}

	public void setYsDeliveryLipss(Iterable<YsDeliveryLips> ysDeliveryLipss) {
		this.ysDeliveryLipss = Sets.newHashSet(ysDeliveryLipss);
	}

	public void setPackType(YsPackType packType) {
		this.packType = packType;
	}

	public void setTransType(YsTransType transType) {
		this.transType = transType;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
