package com.hengyi.japp.crm.web.storage;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.web.AbstractController;

@Named
@Scope("view")
public class StorageController extends AbstractController {
	private static final long serialVersionUID = 3708518912737819900L;
	private Long nodeId;
	private Storage storage;

	public Storage getStorage() {
		if (storage == null)
			storage = new Storage();
		return storage;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
		if (storage == null)
			storage = storageService.findOne(nodeId);
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public void save() {
		try {
			storageService.save(getStorage());
			redirect(URL.CUSTOMERS);
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}
}
