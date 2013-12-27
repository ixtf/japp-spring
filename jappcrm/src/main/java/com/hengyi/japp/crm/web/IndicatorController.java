package com.hengyi.japp.crm.web;

import java.util.List;

import javax.annotation.PostConstruct;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.event.IndicatorUpdateEvent;
import com.hengyi.japp.crm.service.IndicatorService;

public abstract class IndicatorController<T extends Indicator> extends
		AbstractController {
	private IndicatorService<T> indicatorService;
	private Long nodeId;
	private T indicator;
	private List<IndicatorValueScore> indicatorValueScores;
	private IndicatorValueScore selectedIndicatorValueScore;

	@PostConstruct
	protected void init() {
		indicatorService = getIndicatorService();
	}

	protected abstract IndicatorService<T> getIndicatorService();

	public void save() {
		try {
			getIndicator().setOperator(cacheService.getCurrentOperator());
			indicatorService.save(indicator, getIndicatorValueScores());
			operationSuccessMessage();
			eventPublisher.publish(new IndicatorUpdateEvent(indicator
					.getNodeId()));
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

	public T getIndicator() {
		return indicator != null ? indicator : initIndicator();
	}

	protected T initIndicator() {
		if (nodeId == null)
			indicator = indicatorService.newIndicator();
		else {
			indicator = indicatorService.findOne(nodeId);
			indicatorValueScores = indicator.getIndicatorValueScores();
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
		if (getIndicator().getCrmField() == null)
			return true;
		return false;
	}
}
