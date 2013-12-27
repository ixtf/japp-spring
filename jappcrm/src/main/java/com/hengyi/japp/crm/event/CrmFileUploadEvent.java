package com.hengyi.japp.crm.event;

import org.primefaces.model.UploadedFile;
import org.springframework.context.ApplicationEvent;

import com.hengyi.japp.crm.domain.Crm;

public class CrmFileUploadEvent extends ApplicationEvent {
	private static final long serialVersionUID = -5538522740447615400L;
	private final Crm crm;

	public CrmFileUploadEvent(Object source, Crm crm) {
		super(source);
		this.crm = crm;
	}

	public UploadedFile getUploadedFile() {
		return (UploadedFile) getSource();
	}

	public Crm getCrm() {
		return crm;
	}
}
