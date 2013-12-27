package com.hengyi.japp.crm.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class HomeController extends AbstractController implements Serializable {
	private static final long serialVersionUID = -1470555038437736246L;

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
