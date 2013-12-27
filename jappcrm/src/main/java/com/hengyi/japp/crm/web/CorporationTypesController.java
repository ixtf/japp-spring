package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.CorporationType;

@Named
@Scope("request")
public class CorporationTypesController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<CorporationType> corporationTypes;
	private CorporationType corporationType;

	public List<CorporationType> getCorporationTypes() {
		return corporationTypes;
	}

	public CorporationType getCorporationType() {
		return corporationType;
	}

	public void setCorporationType(CorporationType corporationType) {
		this.corporationType = corporationType;
	}

	public void edit() {
		redirect(corporationTypeService.getUpdatePath(getCorporationType()
				.getNodeId()));
	}

	public void delete() {
		try {
			corporationTypeService.delete(corporationType);
			corporationTypes.remove(corporationType);
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	@PostConstruct
	public void init() {
		corporationTypes = Lists.newArrayList(corporationTypeService.findAll());
	}
}
