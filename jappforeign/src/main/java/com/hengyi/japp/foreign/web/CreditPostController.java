package com.hengyi.japp.foreign.web;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.foreign.application.event.vbak.CreditPostWriteToSapEvent;
import com.hengyi.japp.foreign.domain.CreditPost;
import com.hengyi.japp.foreign.domain.CreditPostRecieveInfo;

@Named
@Scope("session")
public class CreditPostController extends AbstractController {
	private static final long serialVersionUID = 4456255104731446014L;
	@NotBlank
	protected String number;
	private String vbakVbeln;

	public String open() {
		init();
		return "pretty:creditPostUpdate";
	}

	public String save() {
		try {
			// creditPostModel.getCreditPost().setOperator(
			// cacheService.getCurrentOperator());
			// creditPostService.saveCreditPost(creditPostModel);
			eventPublisher.publish(new CreditPostWriteToSapEvent(getNumber()));
			addInfoMessage("保存成功！");
			return "pretty:creditPostManage";
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
		return null;
	}

	public void openCreditPost() {
		// if (creditPostModel != null
		// && getCreditPost().getNumber().equals(getNumber()))
		// return;

		init();
		try {
			// creditPostModel =
			// creditPostService.findOneCreditModel(getNumber());
		} catch (Exception e) {
			addRootErrorMessage(e);
			init();
		}
	}

	public void addVbak() {
		try {
			// VbakModel vbakModel =
			// vbakService.findOneVbakModel(getVbakVbeln());
			// if (!creditPostModel.getVbakModels().contains(vbakModel))
			// creditPostModel.getVbakModels().add(vbakModel);
			// setVbakVbeln(vbakModel.getVbak().getVbeln());
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
	}

	public void removeVbak() {
		// creditPostModel.getVbakModels().remove(selectedVbakModel);
	}

	public CreditPost getCreditPost() {
		return null;
		// return creditPostModel.getCreditPost();
	}

	public CreditPostRecieveInfo getRecieveInfo() {
		return null;
		// return creditPostModel.getRecieveInfo();
	}

	public String getNumber() {
		return StringUtils.trimToEmpty(number).toUpperCase();
	}

	public String getVbakVbeln() {
		return StringUtils.trimToEmpty(vbakVbeln);
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setVbakVbeln(String vbakVbeln) {
		this.vbakVbeln = vbakVbeln;
	}

	@PostConstruct
	public void init() {
		vbakVbeln = null;
	}
}
