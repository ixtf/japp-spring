package com.hengyi.japp.foreign.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.application.event.EventPublisher;
import com.hengyi.japp.foreign.application.event.stockprepare.StockPrepareUpdateEvent;
import com.hengyi.japp.foreign.application.event.vbap.StockPrepareWriteToSapEvent;
import com.hengyi.japp.foreign.domain.Operator;
import com.hengyi.japp.foreign.domain.StockPrepare;
import com.hengyi.japp.foreign.domain.StockPrepareInfo;
import com.hengyi.japp.foreign.domain.StockPrepareItem;
import com.hengyi.japp.foreign.domain.Vbap;
import com.hengyi.japp.foreign.domain.repository.StockPrepareRepository;
import com.hengyi.japp.foreign.domain.repository.VbapRepository;
import com.hengyi.japp.foreign.service.StockPrepareService;

@Named
@Transactional
public class StockPrepareServiceImpl implements StockPrepareService {
	@Inject
	private EventPublisher eventPublisher;
	@Inject
	private VbapRepository vbapRepository;
	@Inject
	private StockPrepareRepository stockPrepareRepository;

	@Override
	public void bindVbap(String number, VbapPK pk, Operator operator) {
		deleteByVbap(pk);

		Vbap vbap = vbapRepository.findOne(pk);
		StockPrepare stockPrepare = new StockPrepare(number, operator);
		StockPrepareItem item = new StockPrepareItem(stockPrepare, vbap);
		stockPrepare.setItems(Sets.newHashSet(item));
		stockPrepareRepository.save(stockPrepare);
		stockPrepareRepository.flush();
		eventPublisher.publish(new StockPrepareWriteToSapEvent(pk));
	}

	@Override
	public void finish(String number, Operator operator) {
		StockPrepare stockPrepare = stockPrepareRepository.findOne(number);
		for (StockPrepareItem item : stockPrepare.getItems())
			item.getInfo().setKwmeng(BigDecimal.ONE);

		StockPrepareInfo info = stockPrepare.getInfo();
		if (info == null)
			info = new StockPrepareInfo(stockPrepare, operator);
		info.setOperator(operator);
		stockPrepare.updateStatus();
		stockPrepareRepository.save(stockPrepare);
		stockPrepareRepository.flush();
		eventPublisher.publish(new StockPrepareUpdateEvent(stockPrepare));
	}

	@Override
	public void unfinish(String number, Operator operator) {
		StockPrepare stockPrepare = stockPrepareRepository.findOne(number);
		for (StockPrepareItem item : stockPrepare.getItems())
			item.getInfo().setKwmeng(BigDecimal.ZERO);
		stockPrepare.getInfo().setOperator(operator);
		stockPrepare.updateStatus();
		stockPrepareRepository.save(stockPrepare);
		stockPrepareRepository.flush();
		eventPublisher.publish(new StockPrepareUpdateEvent(stockPrepare));
	}

	private void deleteByVbap(VbapPK pk) {
		List<StockPrepare> stockPrepares = stockPrepareRepository
				.findAll(new StockPrepareRepository.FindByVbap(pk));
		stockPrepareRepository.delete(stockPrepares);
		stockPrepareRepository.flush();
	}
}
