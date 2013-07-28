package com.hengyi.japp.foreign.web;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class StockPrepareInfoController extends StockPrepareController {
	private static final long serialVersionUID = -5175676593480867108L;

	@Override
	public String open() {
		if (!stockPrepareRepository.exists(getNumber())) {
			addRootErrorMessage(new Exception(getNumber() + "不存在！"));
			return null;
		}
		init();
		return "pretty:stockPrepareInfoUpdate";
	}

	@Override
	public String save() {
		try {
			// stockPrepareModel.getStockPrepareInfo().setOperator(
			// cacheService.getCurrentOperator());
			// stockPrepareService.saveStockPrepareInfo(stockPrepareModel);
			addInfoMessage("保存成功！");
			return "pretty:stockPrepareInfoManage";
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
		return null;
	}

	public void openStockPrepareInfo() {
		// if (stockPrepareModel != null
		// && stockPrepareModel.getStockPrepare().getNumber()
		// .equals(getNumber()))
		return;
	}

	@Override
	@PostConstruct
	public void init() {
	}
}
