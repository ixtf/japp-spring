package com.hengyi.japp.crm.application;

import java.io.File;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmFile;
import com.hengyi.japp.crm.domain.repository.UploadFileRepository;
import com.hengyi.japp.crm.event.CrmFileRemoveEvent;

@Named
@Transactional
public class CrmFileRemoveEventListener implements
		ApplicationListener<CrmFileRemoveEvent> {
	private final String CRMDIR = "CRM[%d]";
	private String UPLOADFILEDIR = null;
	@Resource(name = "deployProperties")
	private Properties deployProperties;
	@Inject
	private EventBus eventBus;
	@Inject
	private UploadFileRepository uploadFileRepository;

	@PostConstruct
	public void init() {
		UPLOADFILEDIR = deployProperties.getProperty("uploadFileDir");
	}

	@Override
	public void onApplicationEvent(CrmFileRemoveEvent event) {
		CrmFile crmFile = uploadFileRepository.findOne(event
				.getUploadFileNodeId());
		File file = getFile(crmFile.getCrm(), crmFile.getFileName());
		if (file.exists())
			file.delete();
		uploadFileRepository.delete(crmFile);
		eventBus.post(event);
	}

	private File getFile(Crm crm, String fileName) {
		File crmDir = new File(UPLOADFILEDIR, String.format(CRMDIR,
				crm.getNodeId()));
		return new File(crmDir, fileName);
	}
}
