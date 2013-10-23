package com.hengyi.japp.crm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.service.CrmService;

public abstract class CrmController<T extends Crm> extends AbstractController {
    private CrmService<T> crmService;
    private Long nodeId;
    private T crm;
    @NotNull
    @Size(min = 1)
    private List<CrmType> crmTypes;
    @NotNull
    @Size(min = 1)
    private List<Certificate> certificates;
    @NotNull
    @Size(min = 1)
    // Crm关联的指标值，已经被选中，用于保存
    private Map<Indicator, List<IndicatorValueScore>> indicatorMap;
    @NotNull
    // 主要联系人，用于保存
    private Communicatee communicatee;
    // 添加的联系人，用于保存
    private List<Communicatee> communicatees;
    /*
     * 前台在所有联系人中选中的联系人
     * 
     * 选中的联系人可以加为主要联系人，或者添加联系人
     */
    private Communicatee selectedCommunicatee;
    // 当前Crm关联的Crm，已经关联的，用于保存
    private List<Associate> associates;
    private Associate selectedAssociate;
    // 可以输入的所有指标
    private List<Indicator> indicators;
    /*
     * 前台选中的指标值，可以是已选的，也可以是未选的
     */
    private IndicatorValueScore selectedIndicatorValueScore;

    @PostConstruct
    protected void init() {
	crmService = getCrmService();

	ImmutableList.Builder<Indicator> builder = ImmutableList.builder();
	for (Indicator indicator : crmService.findAllIndicator())
	    if (!indicator.getIndicatorValueScores().isEmpty())
		builder.add(indicator);
	indicators = builder.build();
    }

    protected abstract CrmService<T> getCrmService();

    public void save() {
	try {
	    getCrm().setOperator(cacheService.getCurrentOperator());
	    crmService.save(crm, getIndicatorMap(), getCrmTypes(),
		    getCertificates(), getCommunicatee(), getCommunicatees(),
		    getAssociates());
	    operationSuccessMessage();
	} catch (Exception e) {
	    errorMessage(e);
	}
    }

    public void addIndicatorValueScore() {
	List<IndicatorValueScore> list = getIndicatorMap().get(
		selectedIndicatorValueScore.getStart());
	if (!list.contains(selectedIndicatorValueScore))
	    list.add(selectedIndicatorValueScore);
    }

    public void removeIndicatorValueScore() {
	getIndicatorMap().get(selectedIndicatorValueScore.getStart()).remove(
		selectedIndicatorValueScore);
    }

    public T getCrm() {
	if (crm != null)
	    return crm;
	if (nodeId == null)
	    crm = crmService.newCrm();
	else
	    crm = crmService.findOne(nodeId);
	return crm;
    }

    public List<CrmType> getCrmTypes() {
	if (crmTypes == null)
	    crmTypes = Lists.newArrayList(getCrm().getCrmTypes());
	return crmTypes;
    }

    public List<Certificate> getCertificates() {
	if (certificates == null)
	    certificates = Lists.newArrayList(getCrm().getCertificates());
	return certificates;
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
	return indicators;
    }

    public Map<Indicator, List<IndicatorValueScore>> getIndicatorMap() {
	if (indicatorMap != null)
	    return indicatorMap;
	ImmutableMap.Builder<Indicator, List<IndicatorValueScore>> builder = ImmutableMap
		.builder();
	Map<Indicator, List<IndicatorValueScore>> map = crmService
		.getIndicatorMap(getCrm(), getIndicators());
	for (Indicator indicator : getIndicators()) {
	    List<IndicatorValueScore> list = map.get(indicator);
	    if (list == null)
		list = Lists.newArrayList();
	    builder.put(indicator, list);
	}
	indicatorMap = builder.build();
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

    public void setCrmTypes(List<CrmType> crmTypes) {
	this.crmTypes = crmTypes;
    }

    public void setCertificates(List<Certificate> certificates) {
	this.certificates = certificates;
    }

    public IndicatorValueScore getSelectedIndicatorValueScore() {
	return selectedIndicatorValueScore;
    }

    public void setSelectedIndicatorValueScore(
	    IndicatorValueScore selectedIndicatorValueScore) {
	this.selectedIndicatorValueScore = selectedIndicatorValueScore;
    }
}
