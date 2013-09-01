package com.hengyi.japp.crm.web;

import java.util.List;
import java.util.Set;

import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.web.data.AssociateModel;

@Named
@Scope("view")
public class CrmController extends AbstractController {
	private static final long serialVersionUID = 3708518912737819900L;
	private Long nodeId;
	private Crm crm;
	private Customer customer;
	@NotNull
	private Long crmTypeNodeId;
	@NotNull
	private Communicatee communicatee;
	private List<Communicatee> communicatees;
	private Communicatee selectedCommunicatee;
	private List<AssociateModel> associateModels;
	private AssociateModel associateModel;

	public void save() {
		try {
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
		if (!associateModel.getAssociateCrm().equals(getCustomer())
				&& !getAssociateModels().contains(associateModel))
			getAssociateModels().add(associateModel);
	}

	public void removeAssociateModel() {
		getAssociateModels().remove(associateModel);
	}

	public Customer getCustomer() {
		return customer != null ? customer : initCustomer();
	}

	private Customer initCustomer() {
		if (nodeId == null)
			customer = new Customer();
		else {
			customer = customerService.findOne(nodeId);
			communicatee = customer.getCommunicatee();
			communicatees = Lists.newArrayList(customer
					.getCommunicatees(template));
			crmTypeNodeId = customer.getCrmType().getNodeId();
			associateModels = Lists.newArrayList();
			for (Associate associate : customer.getAssociates(template)) {
				AssociateModel model = new AssociateModel(customer);
				model.setNote(associate.getNote());
				if (!associate.getStart(template).equals(customer))
					model.setAssociateCrm(associate.getStart());
				else
					model.setAssociateCrm(associate.getEnd(template));
				associateModels.add(model);
			}
		}
		return customer;
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

	public Long getCrmTypeNodeId() {
		return crmTypeNodeId;
	}

	public void setCrmTypeNodeId(Long crmTypeNodeId) {
		this.crmTypeNodeId = crmTypeNodeId;
	}

	public List<AssociateModel> getAssociateModels() {
		if (associateModels == null)
			associateModels = Lists.newArrayList();
		return associateModels;
	}

	public AssociateModel getAssociateModel() {
		if (associateModel == null)
			associateModel = new AssociateModel(getCustomer());
		return associateModel;
	}

	public void setAssociateModel(AssociateModel associateModel) {
		this.associateModel = associateModel;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
}
