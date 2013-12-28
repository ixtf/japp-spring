package com.hengyi.japp.crm.event;

import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationEvent;

public class CrmFileUploadEvent extends ApplicationEvent {
	private static final long serialVersionUID = -5538522740447615400L;
	private final Long crmNodeId;
	private final UploadedFile uploadedFile;

	public CrmFileUploadEvent(Long operatorNodeId, Long crmNodeId,
			UploadedFile uploadedFile) {
		super(operatorNodeId);
		this.crmNodeId = crmNodeId;
		this.uploadedFile = uploadedFile;
	}

	public Long getOperatorNodeId() {
		return (Long) getSource();
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public Long getCrmNodeId() {
		return crmNodeId;
	}
}
