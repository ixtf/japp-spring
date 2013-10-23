package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.common.web.model.LazyNeo4jModel;
import com.hengyi.japp.crm.domain.Communicatee;

@Named
@Scope("view")
public class CommunicateesController extends AbstractController implements
	Serializable {
    private static final long serialVersionUID = -6359781138513690580L;
    private LazyNeo4jModel<Communicatee> communicatees;
    private Communicatee communicatee;
    private String nameSearch;
    private List<Communicatee> searchResult;

    public void delete() {
	try {
	    communicateeService.delete(communicatee);
	    if (searchResult != null)
		searchResult.remove(communicatee);
	    operationSuccessMessage();
	} catch (Exception e) {
	    errorMessage(e);
	}
    }

    public void search() {
	try {
	    searchResult = communicateeService.findAllByQuery(nameSearch);
	    communicatees = new LazyNeo4jModel<Communicatee>(searchResult);
	} catch (Exception e) {
	    errorMessage(e);
	}
    }

    public void edit() {
	redirect(communicateeService.getManagePath() + "/"
		+ getCommunicatee().getNodeId());
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

    public LazyNeo4jModel<Communicatee> getCommunicatees() {
	if (communicatees == null)
	    communicatees = new LazyNeo4jModel<Communicatee>(
		    communicateeService);
	return communicatees;
    }

    public List<Communicatee> getSearchResult() {
	return searchResult;
    }
}
