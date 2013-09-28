package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.IndicatorValue;

@Named
@Scope("view")
public class IndicatorValuesController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<IndicatorValue> indicatorValues;
	private IndicatorValue indicatorValue;
	private String nameSearch;

	public void search() {
		indicatorValues = indicatorValueService.findAllByQuery(nameSearch);
	}

	public void edit() {
		redirect(indicatorValueService.getUpdatePath(getIndicatorValue()
				.getNodeId()));
	}

	public void delete() {
		try {
			indicatorValueService.delete(indicatorValue);
			indicatorValues.remove(indicatorValue);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public List<IndicatorValue> getIndicatorValues() {
		if (indicatorValues == null)
			indicatorValues = indicatorValueService.findAll();
		return indicatorValues;
	}

	public IndicatorValue getIndicatorValue() {
		return indicatorValue;
	}

	public void setIndicatorValue(IndicatorValue indicatorValue) {
		this.indicatorValue = indicatorValue;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
}
