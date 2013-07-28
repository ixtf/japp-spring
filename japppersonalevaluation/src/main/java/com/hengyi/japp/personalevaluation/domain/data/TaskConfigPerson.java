package com.hengyi.japp.personalevaluation.domain.data;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Min;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.hengyi.japp.personalevaluation.domain.node.Operator;
import com.hengyi.japp.personalevaluation.domain.node.SubmitLimit;

public class TaskConfigPerson implements Serializable {
	public static final int LAYS_ALL = 0;
	protected static final long serialVersionUID = 4675998867550488778L;
	protected Long nodeId;
	protected boolean statistic = true;
	protected boolean evaluable = true;
	@Min(0)
	protected int lays;
	protected Set<SubmitLimit> submitLimits;
	protected Set<TaskConfigKpi> kpis;
	protected Long managerNodeId;

	public void addSubmitLimit(SubmitLimit submitLimit) {
		getSubmitLimits().add(submitLimit);
	}

	public void removeSubmitLimit(SubmitLimit submitLimit) {
		getSubmitLimits().remove(submitLimit);
	}

	public void addKpi(TaskConfigKpi kpi) {
		getKpis().add(kpi);
	}

	public void removeKpi(TaskConfigKpi kpi) {
		getKpis().remove(kpi);
	}

	public Set<SubmitLimit> getSubmitLimits() {
		if (submitLimits == null)
			submitLimits = Sets.newHashSet();
		return submitLimits;
	}

	public Set<TaskConfigKpi> getKpis() {
		if (kpis == null)
			kpis = Sets.newHashSet();
		return kpis;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public boolean isStatistic() {
		return statistic;
	}

	public boolean isEvaluable() {
		return evaluable;
	}

	public int getLays() {
		return lays;
	}

	public Long getManagerNodeId() {
		return managerNodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setStatistic(boolean statistic) {
		this.statistic = statistic;
	}

	public void setEvaluable(boolean evaluable) {
		this.evaluable = evaluable;
	}

	public void setLays(int lays) {
		this.lays = lays;
	}

	public void setSubmitLimits(Set<SubmitLimit> submitLimits) {
		this.submitLimits = submitLimits;
	}

	public void setKpis(Set<TaskConfigKpi> kpis) {
		this.kpis = kpis;
	}

	public void setManagerNodeId(Long managerNodeId) {
		this.managerNodeId = managerNodeId;
	}

	public TaskConfigPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskConfigPerson(Long nodeId) {
		super();
		this.nodeId = nodeId;
	}

	public TaskConfigPerson(Operator operator) {
		this(operator.getNodeId());
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (getNodeId() == null)
			return false;
		final TaskConfigPerson other = (TaskConfigPerson) o;
		return Objects.equal(getNodeId(), other.getNodeId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNodeId());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(getNodeId()).toString();
	}

	public void setOperator(Operator operator) {
		this.nodeId = operator != null ? operator.getNodeId() : null;
	}

	public void setManager(Operator manager) {
		this.managerNodeId = manager != null ? manager.getNodeId() : null;
	}
}
