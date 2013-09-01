package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.web.data.LazyCommunicateeModel;

@Named
@Scope("view")
public class CommunicateesController extends AbstractController implements Serializable{
	private static final long serialVersionUID = -6359781138513690580L;
	private LazyCommunicateeModel lazyCommunicateeModel;
	private Communicatee communicatee;
	private String nameSearch;
	private List<Communicatee> searchResult;

	public void delete() {
		try {
			communicateeService.delete(communicatee);
			if (searchResult != null)
				searchResult.remove(communicatee);
			addInfoMessage("删除成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = communicateeService.findAllByQuery(nameSearch);
			lazyCommunicateeModel = new LazyCommunicateeModel(searchResult);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void edit() {
		redirect(URL.COMMUNICATEES + "/" + getCommunicatee().getNodeId());
	}

	public Communicatee getCommunicatee() {
		return communicatee;
	}

	public void setCommunicatee(Communicatee communicatee) {
		this.communicatee = communicatee;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public LazyCommunicateeModel getLazyCommunicateeModel() {
		if (lazyCommunicateeModel == null)
			lazyCommunicateeModel = new LazyCommunicateeModel(
					communicateeService);
		return lazyCommunicateeModel;
	}

	public List<Communicatee> getSearchResult() {
		return searchResult;
	}
}
