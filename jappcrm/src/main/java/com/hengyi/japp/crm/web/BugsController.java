package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.Bug;
import com.hengyi.japp.crm.web.data.LazyBugModel;

@Named
@Scope("view")
public class BugsController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private LazyBugModel lazyBugModel;
	private Bug bug;
	private String nameSearch;
	private List<Bug> searchResult;

	public void delete() {
		try {
			bugService.delete(bug);
			if (searchResult != null)
				searchResult.remove(bug);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = bugService.findAllByQuery(nameSearch);
			lazyBugModel = new LazyBugModel(searchResult);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void edit() {
		redirect(urlUtil.getBugsPath() + "/" + getBug().getNodeId());
	}

	public LazyBugModel getLazyBugModel() {
		return lazyBugModel;
	}

	public void setLazyBugModel(LazyBugModel lazyBugModel) {
		this.lazyBugModel = lazyBugModel;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}
}
