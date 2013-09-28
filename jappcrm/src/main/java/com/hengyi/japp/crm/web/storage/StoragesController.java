package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.web.CrmsController;

@Named
@Scope("view")
public class StoragesController extends CrmsController<Storage> implements
		Serializable {
	private static final long serialVersionUID = 3708518912737819900L;

	public void edit() {
		redirect(urlUtil.getStoragesPath() + "/" + getCrm().getNodeId());
	}

	@Override
	protected CrmService<Storage> getCrmService() {
		return storageService;
	}
}
