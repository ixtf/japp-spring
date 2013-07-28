package com.hengyi.japp.personalevaluation.web;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hengyi.japp.personalevaluation.domain.node.Kpi;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;

public class KpiEvaluationRow {
	private Kpi kpi;
	private List<KpiEvaluation> kpiEvaluations;
	private Map<Integer, KpiEvaluation> kpiEvaluationMap;

	public Kpi getKpi() {
		return kpi;
	}

	public List<KpiEvaluation> getKpiEvaluations() {
		if (kpiEvaluations == null)
			kpiEvaluations = Lists.newArrayList();
		return kpiEvaluations;
	}

	public void setKpi(Kpi kpi) {
		this.kpi = kpi;
	}

	public void setKpiEvaluations(List<KpiEvaluation> kpiEvaluations) {
		this.kpiEvaluations = kpiEvaluations;
	}

	public Map<Integer, KpiEvaluation> getKpiEvaluationMap() {
		if (kpiEvaluationMap == null)
			initKpiEvaluationMap();
		return kpiEvaluationMap;
	}

	private void initKpiEvaluationMap() {
		kpiEvaluationMap = Maps.newHashMap();
		for (int i = 0; i < getKpiEvaluations().size(); i++)
			kpiEvaluationMap.put(i, getKpiEvaluations().get(i));
	}
}
