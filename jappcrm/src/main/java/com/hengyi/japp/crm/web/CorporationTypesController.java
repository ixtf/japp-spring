package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.hengyi.japp.crm.Constant.Url;
import com.hengyi.japp.crm.dto.CorporationTypeDTO;
import com.hengyi.japp.crm.event.CorporationTypeDeleteEvent;

@Named
@Scope("view")
public class CorporationTypesController extends AbstractController implements
		Serializable {
	private static final long serialVersionUID = -6359781138513690580L;
	private List<CorporationTypeDTO> corporationTypes;
	private CorporationTypeDTO corporationType;

	@PostConstruct
	public void init() {
		corporationTypes = queryService.findAllCorporationType();
	}

	public void edit() {
		redirect(Url.corporationType.getUpdatePath(getCorporationType()
				.getNodeId()));
	}

	public void delete() {
		try {
			eventPublisher.publish(new CorporationTypeDeleteEvent(
					getCurrentOperator().getNodeId(), getCorporationType()
							.getNodeId()));
			operationSuccessMessage();
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	public List<CorporationTypeDTO> getCorporationTypes() {
		return corporationTypes;
	}

	public CorporationTypeDTO getCorporationType() {
		return corporationType;
	}

	public void setCorporationType(CorporationTypeDTO corporationType) {
		this.corporationType = corporationType;
	}
}
