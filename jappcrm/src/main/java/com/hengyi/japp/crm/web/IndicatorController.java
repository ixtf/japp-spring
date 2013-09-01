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
			indicatorService.save(indicator, indicatorValueScores);
			addInfoMessage("保存成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void addIndicatorValueScore() {
		if (!getIndicatorValueScores().contains(
				getSelectedIndicatorValueScore()))
			indicatorValueScores.add(selectedIndicatorValueScore);
		selectedIndicatorValueScore = null;
	}

	public void removeIndicatorValueScore() {
		getIndicatorValueScores().remove(getSelectedIndicatorValueScore());
		selectedIndicatorValueScore = null;
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
}
