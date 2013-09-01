package com.hengyi.japp.crm.web;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.IndicatorValue;

@Named
@Scope("view")
public class IndicatorValueController extends AbstractController {
	private static final long serialVersionUID = -6359781138513690580L;
	private Long nodeId;
	private IndicatorValue indicatorValue;

	public void save() {
		try {
			indicatorValueService.save(indicatorValue);
			push("test");
			redirect(URL.INDICATORVALUES);
		} catch (Exception e) {
			addErrorMessage(e);
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
