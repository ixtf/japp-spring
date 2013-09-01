package com.hengyi.japp.crm.web.customer;

import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.web.AbstractController;

@Named
@Scope("view")
public class CustomerIndicatorController extends AbstractController {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private CustomerIndicator indicator;
	private List<IndicatorValueScore> indicatorValueScores;
	private IndicatorValue selectedIndicatorValue;

	public void save() {
		try {
			customerService.save(indicator, indicatorValueScores);
			addInfoMessage("保存成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void addIndicatorValueScore() {
		for (IndicatorValueScore indicatorValueScore : getIndicatorValueScores())
			if (selectedIndicatorValue.equals(indicatorValueScore.getEnd()))
				return;
		getIndicatorValueScores().add(
				new IndicatorValueScore(indicator, selectedIndicatorValue));
	}

	public void removeIndicatorValueScore() {
		for (IndicatorValueScore indicatorValueScore : getIndicatorValueScores())
			if (selectedIndicatorValue.equals(indicatorValueScore.getEnd()))
				getIndicatorValueScores().remove(indicatorValueScore);
	}

	public Indicator getIndicator() {
		return indicator != null ? indicator : initIndicator();
	}

	private Indicator initIndicator() {
		if (nodeId == null)
			indicator = new CustomerIndicator();
		else {
			indicator = customerService.findOneIndicator(nodeId);
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

	public IndicatorValue getSelectedIndicatorValue() {
		return selectedIndicatorValue;
	}

	public void setSelectedIndicatorValue(IndicatorValue selectedIndicatorValue) {
		this.selectedIndicatorValue = selectedIndicatorValue;
	}
}
