package com.hengyi.japp.crm.web.storage;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.storage.StorageService;
import com.hengyi.japp.crm.web.CrmController;

@Named
@Scope("view")
public class StorageController extends CrmController<Storage> implements
		Serializable {
	private static final long serialVersionUID = -4189240961754260470L;
	@Inject
	private StorageService storageService;

	@Override
	protected CrmService<Storage> getCrmService() {
		return storageService;
	}
}
