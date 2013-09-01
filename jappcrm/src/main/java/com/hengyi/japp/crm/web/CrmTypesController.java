package com.hengyi.japp.crm.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.Constant.URL;
import com.hengyi.japp.crm.domain.CrmType;

@Named
@Scope("request")
public class CrmTypesController extends AbstractController implements Serializable{
	private static final long serialVersionUID = -6359781138513690580L;
	private List<CrmType> crmTypes;
	private CrmType crmType;

	public List<CrmType> getCrmTypes() {
		return crmTypes;
	}

	public CrmType getCrmType() {
		return crmType;
	}

	public void setCrmType(CrmType crmType) {
		this.crmType = crmType;
	}

	public void edit() {
		redirect(URL.CRMTYPES + "/" + getCrmType().getNodeId());
	}

	public void delete() {
		try {
			crmTypeRepository.delete(crmType);
			crmTypes.remove(crmType);
			addInfoMessage("删除成功！");
		} catch (Exception e) {
			addErrorMessage(e);
		}
	}

	@PostConstruct
	public void init() {
		crmTypes = Lists.newArrayList(crmTypeRepository.findAll());
	}
}
