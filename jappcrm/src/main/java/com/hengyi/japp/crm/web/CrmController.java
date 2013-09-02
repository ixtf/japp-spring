package com.hengyi.japp.crm.web;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;

public abstract class CrmController extends AbstractController {
	private Long nodeId;
	private Crm crm;
	@NotNull
	private CrmType crmType;
	@NotNull
	@Size(min = 1)
	// Crm关联的指标值，用于保存
	private Map<Indicator, List<IndicatorValueScore>> indicatorMap;
	@NotNull
	// 主要联系人，用于保存
	private Communicatee communicatee;
	// 添加的联系人，用于保存
	private List<Communicatee> communicatees;
	// 当前Crm关联的Crm，用于保存
	private List<Associate> associates;
	/*
	 * 前台在所有联系人中选中的联系人
	 * 
	 * 选中的联系人可以加为主要联系人，或者添加联系人
	 */
	private Communicatee selectedCommunicatee;
	private Associate selectedAssociate;
	/*
	 * 可以输入的所有指标
	 */
	private List<Indicator> indicators;
	/*
	 * 当前前台操作的指标
	 */
	private Indicator selectedIndicator;
	/*
	 * 前台选中的指标值，可以是已选的，也可以是未选的
	 */
	private IndicatorValueScore selectedIndicatorValueScore;

	protected abstract Crm newCrm();

	protected abstract Iterable<Indicator> getAssociatedIndicators();

	public void save() {
		try {
			crmService.save(getCrm(), getIndicatorMap(), getCrmType(),
					getCommunicatee(), getCommunicatees(), getAssociates());
			addInfoMessage("保存成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void addIndicatorValueScore() {
		List<IndicatorValueScore> list = getIndicatorMap().get(
				getSelectedIndicator());
		if (selectedIndicatorValueScore != null
				&& list.contains(selectedIndicatorValueScore))
			list.add(selectedIndicatorValueScore);
	}

	public void removeIndicatorValueScore() {
		getIndicatorMap().get(getSelectedIndicator()).remove(
				selectedIndicatorValueScore);
	}

	public Crm getCrm() {
		if (crm != null)
			return crm;
		if (nodeId == null)
			crm = newCrm();
		else
			crm = crmService.findOne(nodeId);
		return crm;
	}

	public CrmType getCrmType() {
		if (crmType == null)
			crmType = getCrm().getCrmType();
		return crmType;
	}

	public Communicatee getCommunicatee() {
		if (communicatee == null)
			communicatee = getCrm().getCommunicatee();
		return communicatee;
	}

	public List<Communicatee> getCommunicatees() {
		if (communicatees == null)
			communicatees = Lists.newArrayList(getCrm().getCommunicatees(
					template));
		return communicatees;
	}

	public List<Associate> getAssociates() {
		if (associates != null)
			return associates;
		associates = Lists.newArrayList();
		for (Associate associate : getCrm().getAssociates()) {
			Crm end = associate.getAssociate(getCrm(), template);
			associate.setEnd(end);
			associate.setStart(getCrm());
			associates.add(associate);
		}
		return associates;
	}

	public List<Indicator> getIndicators() {
		if (indicators != null)
			return indicators;
		ImmutableList.Builder<Indicator> builder = ImmutableList.builder();
		for (Indicator indicator : getAssociatedIndicators())
			if (!indicator.getIndicatorValueScoresAsList(template).isEmpty())
				builder.add(indicator);
		indicators = builder.build();
		return indicators;
	}

	public Map<Indicator, List<IndicatorValueScore>> getIndicatorMap() {
		// TODO
		if (indicatorMap != null)
			return indicatorMap;
		for (Indicator indicator : getIndicators()) {

		}
		// ImmutableMap.Builder<Indicator, List<IndicatorValue>> builder =
		// ImmutableMap
		// .builder();
		// for (Indicator indicator : getAssociatedIndicators()) {
		// List<IndicatorValue> indicatorValues = ImmutableList
		// .copyOf(indicator.getIndicatorValues(template));
		// if (!indicatorValues.isEmpty())
		// builder.put(indicator, indicatorValues);
		// }
		// indicatorMap = builder.build();
		return indicatorMap;
	}

	public Communicatee getSelectedCommunicatee() {
		return selectedCommunicatee;
	}

	public void setSelectedCommunicatee(Communicatee selectedCommunicatee) {
		this.selectedCommunicatee = selectedCommunicatee;
	}

	public void chiefCommunicatee() {
		communicatee = selectedCommunicatee;
		removeCommunicatee();
	}

	public void addCommunicatee() {
		if (!selectedCommunicatee.equals(getCommunicatee())
				&& !getCommunicatees().contains(selectedCommunicatee))
			getCommunicatees().add(selectedCommunicatee);
	}

	public void removeCommunicatee() {
		getCommunicatees().remove(selectedCommunicatee);
	}

	public Associate getSelectedAssociate() {
		if (selectedAssociate == null)
			selectedAssociate = new Associate(getCrm());
		return selectedAssociate;
	}

	public void setSelectedAssociate(Associate selectedAssociate) {
		this.selectedAssociate = selectedAssociate;
	}

	public void addAssociate() {
		if ((!getAssociates().contains(getSelectedAssociate()))
				&& (!(selectedAssociate.getStart() == null
						|| selectedAssociate.getEnd() == null || Objects.equal(
						selectedAssociate.getStart(),
						selectedAssociate.getEnd()))))
			associates.add(selectedAssociate);
		setSelectedAssociate(null);
	}

	public void removeAssociate() {
		getAssociates().remove(getSelectedAssociate());
		setSelectedAssociate(null);
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public void setCrmType(CrmType crmType) {
		this.crmType = crmType;
	}

	public IndicatorValueScore getSelectedIndicatorValueScore() {
		return selectedIndicatorValueScore;
	}

	public void setSelectedIndicatorValueScore(
			IndicatorValueScore selectedIndicatorValueScore) {
		this.selectedIndicatorValueScore = selectedIndicatorValueScore;
	}

	public Indicator getSelectedIndicator() {
		return selectedIndicator;
	}

	public void setSelectedIndicator(Indicator selectedIndicator) {
		this.selectedIndicator = selectedIndicator;
	}
}
