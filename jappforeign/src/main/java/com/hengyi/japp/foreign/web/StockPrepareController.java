package com.hengyi.japp.foreign.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;

import com.hengyi.japp.foreign.domain.StockPrepare;
import com.hengyi.japp.foreign.domain.StockPrepareInfo;
import com.hengyi.japp.foreign.domain.StockPrepareItem;
import com.hengyi.japp.foreign.domain.StockPrepareItemInfo;

@Named
@Scope("session")
public class StockPrepareController extends AbstractController {
	private static final long serialVersionUID = -5175676593480867108L;

	@NotBlank
	protected String number;

	@NotBlank
	private String vbakVbeln;

	public String open() {
		init();
		return "pretty:stockPrepareUpdate";
	}

	public String save() {
		try {
			// stockPrepareModel.getStockPrepare().setOperator(
			// cacheService.getCurrentOperator());
			// stockPrepareService.saveStockPrepare(stockPrepareModel);
			addInfoMessage("保存成功！");
			// init();
			return "pretty:stockPrepareManage";
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
		return null;
	}

	public void openStockPrepare() {
		// if (stockPrepareModel != null
		// && stockPrepareModel.getStockPrepare().getNumber()
		// .equals(getNumber()))
		return;
	}

	public void bindVbakVbeln() {
		try {
			// stockPrepareModel = stockPrepareService.bindVbakVbeln(
			// stockPrepareModel.getStockPrepare(), getVbakVbeln());
		} catch (Exception e) {
			addRootErrorMessage(e);
		}
	}

	public String getNumber() {
		return StringUtils.trimToEmpty(number).toUpperCase();
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getVbakVbeln() {
		return StringUtils.trim(vbakVbeln);
	}

	public void setVbakVbeln(String vbakVbeln) {
		this.vbakVbeln = vbakVbeln;
	}

	public StockPrepare getStockPrepare() {
		return null;
		// return stockPrepareModel.getStockPrepare();
	}

	public List<StockPrepareItem> getStockPrepareItems() {
		return null;
		// return stockPrepareModel.getStockPrepareItems();
	}

	public StockPrepareInfo getStockPrepareInfo() {
		return null;
		// return stockPrepareModel.getStockPrepareInfo();
	}

	public List<StockPrepareItemInfo> getStockPrepareInfoItems() {
		return null;
		// return stockPrepareModel.getStockPrepareInfoItems();
	}

	@PostConstruct
	public void init() {
		vbakVbeln = null;
	}
}
