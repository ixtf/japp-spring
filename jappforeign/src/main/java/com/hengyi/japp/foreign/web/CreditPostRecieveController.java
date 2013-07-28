package com.hengyi.japp.foreign.web;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class CreditPostRecieveController extends CreditPostController {
	private static final long serialVersionUID = 4456255104731446014L;

	@Override
	public String open() {
		if (!creditPostRepository.exists(getNumber())) {
			addRootErrorMessage(new Exception(getNumber() + "不存在！"));
			return null;
		}
		return "pretty:creditPostRecieveUpdate";
	}

	@Override
	public String save() {
		try {
			// creditPostModel.getRecieveInfo().setOperator(
			// cacheService.getCurrentOperator());
			// creditPostService.saveCreditPostRecieveInfo(creditPostModel);
			// eventPublisher.publish(new CreditPostUpdateEvent(
			// getNumber()));
			// eventPublisher.publish(new
			// CreditPostWriteToSapEvent(getNumber()));
			addInfoMessage("保存成功！");
			return "pretty:creditPostRecieveManage";
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
		return null;
	}

	public String openCreditPostRecieve() {
		if (!creditPostRepository.exists(getNumber())) {
			addRootErrorMessage(new Exception(getNumber() + "不存在！"));
			return "pretty:creditPostRecieveManage";
		}

		try {
			// creditPostModel =
			// creditPostService.findOneCreditModel(getNumber());
		} catch (Exception e) {
			addRootErrorMessage(e);
			return "pretty:creditPostRecieveManage";
		}
		return null;
	}
}
