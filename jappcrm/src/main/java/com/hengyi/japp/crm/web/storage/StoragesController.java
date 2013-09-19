package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

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
		redirect(urlUtil.getStoragesPath() + "/" + getStorage().getNodeId());
	}

	public void delete() {
		try {
			crmService.delete(getStorage());
			if (searchResult != null)
				searchResult.remove(getStorage());
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void search() {
		try {
			searchResult = storageService.findAllByQuery(nameSearch);
			lazyStorageModel = new LazyStorageModel(searchResult);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public void basicInfoReport() {
		redirect(urlUtil.getStoragesPath() + "/" + getStorage().getNodeId()
				+ "/basicInfoReport");
	}

	public void creditRiskReport() {
		redirect(urlUtil.getStoragesPath() + "/" + getStorage().getNodeId()
				+ "/creditRiskReport");
	}

	public void fiCreditRiskReport() {
		redirect(urlUtil.getStoragesPath() + "/" + getStorage().getNodeId()
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
