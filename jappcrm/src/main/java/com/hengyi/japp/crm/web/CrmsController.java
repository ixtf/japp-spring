package com.hengyi.japp.crm.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Report;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.ReportService;
import com.hengyi.japp.crm.web.model.LazyCrmModel;

public abstract class CrmsController<CRM extends Crm> extends
		AbstractController {
	private CrmService<CRM> crmService;
	private ReportService<?> reportService;
	private LazyCrmModel<CRM> crms;
	private CRM crm;
	private String nameSearch;
	private List<CRM> searchResult;
	private Report report;

	@PostConstruct
	private void init() {
		crmService = getCrmService();
		reportService = getReportService();
		initReportSubmenu();
	}

	// private UISubmenu reportSubmenu;
	// @SuppressWarnings("unchecked")
	// private void initReportSubmenu() {
	// reportSubmenu = new UISubmenu();
	// for (Report report : reportService.findAll()) {
	// UIMenuItem menuItem = new UIMenuItem();
	// menuItem.setValue(report.getName());
	// menuItem.setTitle(String.valueOf(report.getNodeId()));
	// menuItem.setAjax(false);
	// menuItem.addActionListener(new ActionListener() {
	// @Override
	// public void processAction(ActionEvent event)
	// throws AbortProcessingException {
	// redirect("/crms/" + crm.getNodeId() + "/reports/"
	// + ((UIMenuItem) event.getSource()).getTitle());
	// }
	// });
	// reportSubmenu.getElements().add(menuItem);
	// }
	// }

	private Submenu reportSubmenu;

	private void initReportSubmenu() {
		reportSubmenu = new Submenu();
		for (Report report : reportService.findAll()) {
			MenuItem menuItem = new MenuItem();
			menuItem.setValue(report.getName());
			menuItem.setTitle(String.valueOf(report.getNodeId()));
			menuItem.setAjax(false);
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void processAction(ActionEvent event)
						throws AbortProcessingException {
					redirect("/crms/" + crm.getNodeId() + "/reports/"
							+ ((MenuItem) event.getSource()).getTitle());
				}
			});
			reportSubmenu.getChildren().add(menuItem);
		}
	}

	protected abstract CrmService<CRM> getCrmService();

	protected abstract ReportService<?> getReportService();

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

	public Submenu getReportSubmenu() {
		return reportSubmenu;
	}

	public void setReportSubmenu(Submenu reportSubmenu) {
		this.reportSubmenu = reportSubmenu;
	}
}
