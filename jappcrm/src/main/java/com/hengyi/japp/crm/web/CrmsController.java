package com.hengyi.japp.crm.web;

import java.util.List;

import javax.annotation.PostConstruct;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.web.data.LazyCrmModel;

public abstract class CrmsController<T extends Crm> extends AbstractController {
	private CrmService<T> crmService;
	private LazyCrmModel<T> crms;
	private T crm;
	private String nameSearch;
	private List<T> searchResult;

	@PostConstruct
	private void init() {
		crmService = getCrmService();
	}

	protected abstract CrmService<T> getCrmService();

	public abstract void edit();

	public void delete() {
		try {
			crmService.delete(getCrm());
			if (searchResult != null)
				searchResult.remove(getCrm());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = crmService.findAllByQuery(nameSearch);
			crms = new LazyCrmModel<T>(searchResult);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public LazyCrmModel<T> getCrms() {
		if (crms == null)
			crms = new LazyCrmModel<T>(crmService);
		return crms;
	}

	public T getCrm() {
		return crm;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public List<T> getSearchResult() {
		return searchResult;
	}

	public void setCrm(T crm) {
		this.crm = crm;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
}
