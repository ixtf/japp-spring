package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.IndicatorValue;

@Named
@Scope("view")
public class IndicatorValueController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private IndicatorValue indicatorValue;

	public void save() {
		try {
			getIndicatorValue().setOperator(getCurrentOperator());
			indicatorValueService.save(indicatorValue);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public IndicatorValue getIndicatorValue() {
		return indicatorValue != null ? indicatorValue : initIndicatorValue();
	}

	private IndicatorValue initIndicatorValue() {
		if (nodeId == null)
			indicatorValue = new IndicatorValue();
		else
			indicatorValue = indicatorValueService.findOne(nodeId);
		return indicatorValue;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
}
