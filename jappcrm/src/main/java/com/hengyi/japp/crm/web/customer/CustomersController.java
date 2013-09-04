package com.hengyi.japp.crm.web.customer;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.web.AbstractController;
import com.hengyi.japp.crm.web.data.LazyCustomerModel;

@Named
@Scope("view")
public class CustomersController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 3708518912737819900L;
	private LazyCustomerModel lazyCustomerModel;
	private Customer customer;
	private String nameSearch;
	private List<Customer> searchResult;

	public void edit() {
		redirect(URL.CUSTOMERS + "/" + getCustomer().getNodeId());
	}

	public void delete() {
		try {
			crmService.delete(getCustomer());
			if (searchResult != null)
				searchResult.remove(getCustomer());
			addInfoMessage("删除成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = customerService.findAllByQuery(nameSearch);
			lazyCustomerModel = new LazyCustomerModel(searchResult);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void basicInfoReport() {
		redirect(URL.CUSTOMERS + "/" + getCustomer().getNodeId()
				+ "/report/basicInfo");
	}

	public void creditRiskReport() {
		redirect(URL.CUSTOMERS + "/" + getCustomer().getNodeId()
				+ "/report/creditRisk");
	}

	public void fiCreditRiskReport() {
		redirect(URL.CUSTOMERS + "/" + getCustomer().getNodeId()
				+ "/report/fiCreditRisk");
	}

	public LazyCustomerModel getLazyCustomerModel() {
		if (lazyCustomerModel == null)
			lazyCustomerModel = new LazyCustomerModel(customerService);
		return lazyCustomerModel;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public List<Customer> getSearchResult() {
		return searchResult;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
}
