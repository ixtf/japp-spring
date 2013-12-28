package com.hengyi.japp.crm.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.eventbus.EventBus;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmFile;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.OperatorRepository;
import com.hengyi.japp.crm.domain.repository.UploadFileRepository;
import com.hengyi.japp.crm.event.CrmFileUploadEvent;

@Named
@Transactional
public class CrmFileUploadEventListener implements
		ApplicationListener<CrmFileUploadEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final String CRMDIR = "CRM[%d]";
	private String UPLOADFILEDIR = null;
	@Resource(name = "deployProperties")
	private Properties deployProperties;
	@Inject
	private OperatorRepository operatorRepository;
	@Inject
	private EventBus eventBus;
	@Inject
	private CrmRepository crmRepository;
	@Inject
	private UploadFileRepository uploadFileRepository;

	@PostConstruct
	public void init() {
		UPLOADFILEDIR = deployProperties.getProperty("uploadFileDir");
	}

	@Override
	public void onApplicationEvent(CrmFileUploadEvent event) {
		UploadedFile uploadedFile = event.getUploadedFile();
		Crm crm = crmRepository.findOne(event.getCrmNodeId());
		File file = getFile(crm, uploadedFile);
		if (file.exists()) {
			log.error("{} 文件已经存在！", file.getAbsolutePath());
			throw new RuntimeException(uploadedFile.getFileName() + " 文件已经存在！");
		}
		saveUploadedFile(file, uploadedFile.getContents());
		createCrmFile(event, crm, uploadedFile);
		eventBus.post(event);
	}

	private File getFile(Crm crm, UploadedFile uploadedFile) {
		File crmDir = new File(UPLOADFILEDIR, String.format(CRMDIR,
				crm.getNodeId()));
		crmDir.mkdirs();
		return new File(crmDir, uploadedFile.getFileName());
	}

	private void saveUploadedFile(File file, byte[] contents) {
		OutputStream os = null;
		try {
			file.createNewFile();
			os = new FileOutputStream(file);
			os.write(contents);
		} catch (IOException e) {
			log.error("{} 文件创建失败！{}", file, e);
			throw new RuntimeException(e);
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
					log.error("os 字节流关闭失败！", e);
					throw new RuntimeException(e);
				}
		}
	}

	private void createCrmFile(CrmFileUploadEvent event, Crm crm,
			UploadedFile uploadedFile) {
		CrmFile crmFile = new CrmFile();
		crmFile.setCrm(crm);
		crmFile.setFileName(uploadedFile.getFileName());
		crmFile.setContentType(uploadedFile.getContentType());
		crmFile.setSize(uploadedFile.getSize());
		crmFile.setOperator(operatorRepository.findOne(event
				.getOperatorNodeId()));
		uploadFileRepository.save(crmFile);
	}
}
