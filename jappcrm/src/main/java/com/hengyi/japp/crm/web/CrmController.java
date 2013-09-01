package com.hengyi.japp.crm.web;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.event.FacesEvent;
import javax.validation.constraints.NotNull;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.web.data.AssociateModel;

public abstract class CrmController extends AbstractController {
	private Long nodeId;
	private Crm crm;
	@NotNull
	private CrmType crmType;
	@NotNull
	private Communicatee communicatee;
	private List<Communicatee> communicatees;
	private Communicatee selectedCommunicatee;
	private List<AssociateModel> associateModels;
	private AssociateModel associateModel;
	private List<IndicatorValue> indicatorValues;
	private List<Indicator> indicators;
	private Map<Indicator, List<IndicatorValue>> indicatorMap;
	private List<IndicatorValue> selectedIndicatorValues;

	protected abstract Crm newCrm();

	protected abstract Iterable<Indicator> getAssociatedIndicators();

	public void save() {
		try {
			crmService.save(getCrm(), getIndicatorValues(), getCrmType(),
					getCommunicatee(), getCommunicatees(), getAssociates());
			addInfoMessage("保存成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	private Iterable<Associate> getAssociates() {
		Set<Associate> result = Sets
				.newHashSetWithExpectedSize(getAssociateModels().size());
		for (AssociateModel model : getAssociateModels()) {
			Associate associate = new Associate();
			associate.setStart(model.getCrm());
			associate.setEnd(model.getAssociateCrm());
			associate.setNote(model.getNote());
			result.add(associate);
		}
		return result;
	}

	public void addIndicatorValue() {
		if (selectedIndicatorValues != null)
			indicatorValues.addAll(selectedIndicatorValues);
	}

	public void removeIndicatorValue() {
		if (selectedIndicatorValues != null)
			indicatorValues.removeAll(selectedIndicatorValues);
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

	public void addAssociateModel() {
		if (!associateModel.getAssociateCrm().equals(getCrm())
				&& !getAssociateModels().contains(associateModel))
			getAssociateModels().add(associateModel);
	}

	public void removeAssociateModel() {
		getAssociateModels().remove(associateModel);
	}

	public Crm getCrm() {
		return crm != null ? crm : initCrm();
	}

	private Crm initCrm() {
		if (nodeId == null)
			crm = newCrm();
		else {
			crm = crmService.findOne(nodeId);
			communicatee = crm.getCommunicatee();
			communicatees = Lists.newArrayList(crm.getCommunicatees(template));
			crmType = crm.getCrmType();
			associateModels = Lists.newArrayList();
			for (Associate associate : crm.getAssociates(template)) {
				AssociateModel model = new AssociateModel(crm);
				model.setNote(associate.getNote());
				if (!associate.getStart(template).equals(crm))
					model.setAssociateCrm(associate.getStart());
				else
					model.setAssociateCrm(associate.getEnd(template));
				associateModels.add(model);
			}
		}
		if (indicators == null)
			initIndicators();
		return crm;
	}

	private void initIndicators() {
		if (indicators == null) {
			indicatorMap = Maps.newHashMap();
			for (Indicator indicator : getAssociatedIndicators()) {
				List<IndicatorValue> indicatorValues = Lists
						.newArrayList(indicator.getIndicatorValues(template));
				if (indicatorValues.isEmpty())
					continue;
				indicatorMap.put(indicator, indicatorValues);
			}
		}
		indicators = Lists.newArrayList(indicatorMap.keySet());
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Communicatee getCommunicatee() {
		return communicatee;
	}

	public List<Communicatee> getCommunicatees() {
		if (communicatees == null)
			communicatees = Lists.newArrayList();
		return communicatees;
	}

	public Communicatee getSelectedCommunicatee() {
		return selectedCommunicatee;
	}

	public void setSelectedCommunicatee(Communicatee selectedCommunicatee) {
		this.selectedCommunicatee = selectedCommunicatee;
	}

	public List<AssociateModel> getAssociateModels() {
		if (associateModels == null)
			associateModels = Lists.newArrayList();
		return associateModels;
	}

	public AssociateModel getAssociateModel() {
		if (associateModel == null)
			associateModel = new AssociateModel(getCrm());
		return associateModel;
	}

	public void setAssociateModel(AssociateModel associateModel) {
		this.associateModel = associateModel;
	}

	public CrmType getCrmType() {
		return crmType;
	}

	public void setCrmType(CrmType crmType) {
		this.crmType = crmType;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public Map<Indicator, List<IndicatorValue>> getIndicatorMap() {
		return indicatorMap;
	}

	private List<IndicatorValue> getIndicatorValues() {
		if (indicatorValues == null)
			indicatorValues = Lists.newArrayList();
		return indicatorValues;
	}

	public List<IndicatorValue> getSelectedIndicatorValues() {
		return getIndicatorValues();
	}

	public void setSelectedIndicatorValues(
			List<IndicatorValue> selectedIndicatorValues) {
		this.selectedIndicatorValues = selectedIndicatorValues;
	}

	public void test1(SelectEvent event) {
		System.out.print(event);
		System.out.print(event.getObject());
	}

	public void test2(UnselectEvent event) {
		System.out.print(event);
		System.out.print(event.getObject());
	}
}
