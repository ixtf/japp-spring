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

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.UploadFile;
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
	private UploadFileRepository uploadFileRepository;

	@PostConstruct
	public void init() {
		UPLOADFILEDIR = deployProperties.getProperty("uploadFileDir");
	}

	@Override
	public void onApplicationEvent(CrmFileUploadEvent event) {
		UploadedFile uploadedFile = event.getUploadedFile();
		Crm crm = event.getCrm();
		File file = getFile(crm, uploadedFile);
		if (file.exists()) {
			log.error("{} 文件已经存在！", file.getAbsolutePath());
			throw new RuntimeException();
		}
		createFile(file, uploadedFile.getContents());
		createUploadFile(crm, uploadedFile);
	}

	private File getFile(Crm crm, UploadedFile uploadedFile) {
		File crmDir = new File(UPLOADFILEDIR, String.format(CRMDIR,
				crm.getNodeId()));
		crmDir.mkdirs();
		return new File(crmDir, uploadedFile.getFileName());
	}

	private void createFile(File file, byte[] contents) {
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

	private void createUploadFile(Crm crm, UploadedFile uploadedFile) {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setCrm(crm);
		uploadFile.setFileName(uploadedFile.getFileName());
		uploadFile.setContentType(uploadedFile.getContentType());
		uploadFile.setSize(uploadedFile.getSize());
		uploadFileRepository.save(uploadFile);
	}
}
