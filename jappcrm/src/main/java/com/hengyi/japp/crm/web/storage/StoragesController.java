package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.web.AbstractController;
import com.hengyi.japp.crm.web.data.LazyStorageModel;

@Named
@Scope("view")
public class StoragesController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = 3708518912737819900L;
	private LazyStorageModel lazyStorageModel;
	private Storage storage;
	private String nameSearch;
	private List<Storage> searchResult;

	public void edit() {
		redirect(URL.STORAGES + "/" + getStorage().getNodeId());
	}

	public void delete() {
		try {
			crmService.delete(getStorage());
			if (searchResult != null)
				searchResult.remove(getStorage());
			addInfoMessage("删除成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = storageService.findAllByQuery(nameSearch);
			lazyStorageModel = new LazyStorageModel(searchResult);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	public void basicInfoReport() {
		redirect(URL.CUSTOMERS + "/" + getStorage().getNodeId()
				+ "/basicInfoReport");
	}

	public void creditRiskReport() {
		redirect(URL.CUSTOMERS + "/" + getStorage().getNodeId()
				+ "/creditRiskReport");
	}

	public void fiCreditRiskReport() {
		redirect(URL.CUSTOMERS + "/" + getStorage().getNodeId()
				+ "/fiCreditRiskReport");
	}

	public LazyStorageModel getLazyStorageModel() {
		if (lazyStorageModel == null)
			lazyStorageModel = new LazyStorageModel(storageService);
		return lazyStorageModel;
	}

	public Storage getStorage() {
		return storage;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public List<Storage> getSearchResult() {
		return searchResult;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
}
