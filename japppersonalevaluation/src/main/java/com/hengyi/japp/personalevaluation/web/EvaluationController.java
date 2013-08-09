package com.hengyi.japp.personalevaluation.web;

import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.hengyi.japp.personalevaluation.context.EvaluationPersonContext;
import com.hengyi.japp.personalevaluation.domain.node.Kpi;
import com.hengyi.japp.personalevaluation.domain.node.Person;
import com.hengyi.japp.personalevaluation.domain.node.Task;
import com.hengyi.japp.personalevaluation.domain.relationship.KpiEvaluation;
import com.hengyi.japp.personalevaluation.domain.relationship.LevelEvaluation;

@Named
@Scope("session")
// @Join(path = "/tasks", to = "/faces/task/list.jsf")
public class EvaluationController extends AbstractController {
	private static final long serialVersionUID = 4456255104731446014L;
	private Long personNodeId;
	private EvaluationPersonContext evaluationPersonContext;
	private List<KpiEvaluationRow> kpiEvaluationRows;
	private int levelEvaluationsActiveIndex;

	public void setPersonNodeId(Long personNodeId) {
		if (personNodeId.equals(this.personNodeId))
			return;
		this.personNodeId = personNodeId;
		try {
			Person person = evaluationService.findOnePerson(personNodeId);
			evaluationPersonContext = contextFactory.evaluationContext()
					.evaluationPerson(person);
			initKpiEvaluationRows();
			initLevelEvaluationsActiveIndex();
		} catch (Exception e) {
			addErrorMessage(e);
			this.personNodeId = null;
			this.kpiEvaluationRows = null;
		}
	}

	private void initKpiEvaluationRows() {
		kpiEvaluationRows = Lists.newArrayList();
		Multimap<Kpi, KpiEvaluation> map = getKpiEvaluationMap();
		for (Kpi kpi : map.keySet()) {
			KpiEvaluationRow row = new KpiEvaluationRow();
			row.setKpi(kpi);
			row.setKpiEvaluations(Lists.newArrayList(map.get(kpi)));
			kpiEvaluationRows.add(row);
		}
	}

	private void initLevelEvaluationsActiveIndex() {
		levelEvaluationsActiveIndex = 0;
		for (LevelEvaluation levelEvaluation : getLevelEvaluations()) {
			if (levelEvaluation.getPersonStart().equals(
					evaluationPersonContext.getPersonStart()))
				break;
			levelEvaluationsActiveIndex++;
		}
	}

	public void save() {
		try {
			evaluationPersonContext.save();
			this.personNodeId = null;
			evaluationPersonContext = null;
			kpiEvaluationRows = null;
			redirect("/");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public int getLevelEvaluationsActiveIndex() {
		return levelEvaluationsActiveIndex;
	}

	public List<LevelEvaluation> getLevelEvaluations() {
		return evaluationPersonContext.getLevelEvaluations();
	}

	public List<KpiEvaluationRow> getKpiEvaluationRows() {
		return kpiEvaluationRows;
	}

	public Multimap<Kpi, KpiEvaluation> getKpiEvaluationMap() {
		return evaluationPersonContext.getKpiEvaluationMap();
	}

	public List<KpiEvaluation> getColumns() {
		for (Kpi kpi : getKpiEvaluationMap().keys())
			return Lists.newArrayList(getKpiEvaluationMap().get(kpi));
		return null;
	}

	public Person getPersonStart() {
		return evaluationPersonContext.getPersonStart();
	}

	public Person getPersonEnd() {
		return evaluationPersonContext.getPersonEnd();
	}

	public Task getTask() {
		return evaluationPersonContext.getTask();
	}

	public Long getPersonNodeId() {
		return personNodeId;
	}

	public void setLevelEvaluationsActiveIndex(int levelEvaluationsActiveIndex) {
		this.levelEvaluationsActiveIndex = levelEvaluationsActiveIndex;
	}
}
