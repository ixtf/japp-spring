package com.hengyi.japp.crm.web;

import java.util.List;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public abstract class IndicatorController extends AbstractController {
	private Long nodeId;
	private Indicator indicator;
	private List<IndicatorValueScore> indicatorValueScores;
	private IndicatorValueScore selectedIndicatorValueScore;

	protected abstract Indicator newIndicator();

	public void save() {
		try {
			getIndicator().setOperator(cacheService.getCurrentOperator());
			indicatorService.save(indicator, getIndicatorValueScores());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void addIndicatorValueScore() {
		if (!getIndicatorValueScores().contains(
				getSelectedIndicatorValueScore()))
			indicatorValueScores.add(selectedIndicatorValueScore);
		setSelectedIndicatorValueScore(null);
	}

	public void removeIndicatorValueScore() {
		getIndicatorValueScores().remove(getSelectedIndicatorValueScore());
		setSelectedIndicatorValueScore(null);
	}

	public Indicator getIndicator() {
		return indicator != null ? indicator : initIndicator();
	}

	protected Indicator initIndicator() {
		if (nodeId == null)
			indicator = newIndicator();
		else {
			indicator = indicatorService.findOne(nodeId);
			indicatorValueScores = Lists.newArrayList(indicator
					.getIndicatorValueScores(template));
			for (IndicatorValueScore o : indicatorValueScores)
				o.getEnd(template);
		}
		return indicator;
	}

	public List<IndicatorValueScore> getIndicatorValueScores() {
		if (indicatorValueScores == null)
			indicatorValueScores = Lists.newArrayList();
		return indicatorValueScores;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public IndicatorValueScore getSelectedIndicatorValueScore() {
		if (selectedIndicatorValueScore == null)
			selectedIndicatorValueScore = new IndicatorValueScore(
					getIndicator());
		return selectedIndicatorValueScore;
	}

	public void setSelectedIndicatorValueScore(
			IndicatorValueScore selectedIndicatorValueScore) {
		this.selectedIndicatorValueScore = selectedIndicatorValueScore;
	}

	public boolean isHasIndicatorValues() {
		switch (getIndicator().getIndicatorType()) {
		case SIMPLE:
			return true;
		case CALCULATE:
			return false;
		}
		return false;
	}
}
