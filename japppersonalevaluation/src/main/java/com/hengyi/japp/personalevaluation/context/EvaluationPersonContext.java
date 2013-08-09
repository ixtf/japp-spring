package com.hengyi.japp.personalevaluation.context;

import java.util.List;

import com.google.common.collect.Multimap;
import com.hengyi.japp.personalevaluation.domain.node.Kpi;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.node.TaskConfig;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;

public interface EvaluationPersonContext {
	Task getTask();

	TaskConfig getTaskConfig();

	EvaluationContext getEvaluationContext();

	Person getPersonStart();

	Person getPersonEnd();

	List<LevelEvaluation> getLevelEvaluations();

	Multimap<Kpi, KpiEvaluation> getKpiEvaluationMap();

	void save() throws Exception;
}
