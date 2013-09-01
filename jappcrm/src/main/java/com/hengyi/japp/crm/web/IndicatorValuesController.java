package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.IndicatorValue;

@Named
@Scope("request")
public class IndicatorValuesController extends AbstractController implements Serializable{
	private static final long serialVersionUID = -6359781138513690580L;
	private List<IndicatorValue> indicatorValues;
	private IndicatorValue indicatorValue;
	private String nameSearch;

	public void search() {
		indicatorValues = indicatorValueService.findAllByQuery(nameSearch);
	}

	public void edit() {
		redirect(URL.INDICATORVALUES + "/" + getIndicatorValue().getNodeId());
	}

	public List<IndicatorValue> getIndicatorValues() {
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

	@PostConstruct
	public void init() {
		indicatorValues = indicatorValueService.findAll();
	}
}
