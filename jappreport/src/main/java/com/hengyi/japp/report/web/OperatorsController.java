package com.hengyi.japp.report.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.report.domain.Operator;
import com.hengyi.japp.report.web.data.LazyOperatorModel;

@Named
@Scope("view")
public class OperatorsController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -7807995349596234400L;
	private String nameSearch;
	private LazyOperatorModel operators;
	private List<Operator> searchResult;
	private Operator operator;

	public void edit() {
		redirect(operatorService.getUpdatePath(getOperator().getNodeId()));
	}

	public void search() {
		try {
			searchResult = operatorService.findAllByQuery(nameSearch);
			operators = new LazyOperatorModel(searchResult);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public LazyOperatorModel getOperators() {
		if (operators == null)
			operators = new LazyOperatorModel(operatorService);
		return operators;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public void setOperators(LazyOperatorModel operators) {
		this.operators = operators;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<Operator> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Operator> searchResult) {
		this.searchResult = searchResult;
	}
}
