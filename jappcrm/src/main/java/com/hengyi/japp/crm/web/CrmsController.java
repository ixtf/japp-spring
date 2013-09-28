package com.hengyi.japp.crm.web;

import java.util.List;

import javax.annotation.PostConstruct;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.web.data.LazyCrmModel;

public abstract class CrmsController<CRM extends Crm> extends
		AbstractController {
	private CrmService<CRM> crmService;
	private LazyCrmModel<CRM> crms;
	private CRM crm;
	private String nameSearch;
	private List<CRM> searchResult;
	private Report report;

	@PostConstruct
	private void init() {
		crmService = getCrmService();
	}

	protected abstract CrmService<CRM> getCrmService();

	protected abstract List<? extends Report> getReports();

	public void report() {
		redirect(getCrmService().getUpdatePath(crm.getNodeId()) + "/reports/"
				+ report.getNodeId());
	}

	public void edit() {
		redirect(crmService.getUpdatePath(getCrm().getNodeId()));
	}

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
			crms = new LazyCrmModel<CRM>(searchResult);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public LazyCrmModel<CRM> getCrms() {
		if (crms == null)
			crms = new LazyCrmModel<CRM>(crmService);
		return crms;
	}

	public CRM getCrm() {
		return crm;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public List<CRM> getSearchResult() {
		return searchResult;
	}

	public void setCrm(CRM crm) {
		this.crm = crm;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
}
