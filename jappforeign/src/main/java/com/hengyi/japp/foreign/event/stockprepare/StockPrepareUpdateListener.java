package com.hengyi.japp.foreign.event.stockprepare;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.foreign.domain.StockPrepare;
import com.hengyi.japp.foreign.domain.Vbap;
import com.hengyi.japp.foreign.domain.repository.VbapRepository;
import com.hengyi.japp.foreign.event.EventPublisher;
import com.hengyi.japp.foreign.event.vbap.StockPrepareWriteToSapEvent;
import com.hengyi.japp.foreign.service.StockPrepareService;

@Transactional
@Named
public class StockPrepareUpdateListener implements
		ApplicationListener<StockPrepareUpdateEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected StockPrepareService stockPrepareService;
	@Inject
	private VbapRepository vbapRepository;

	@Override
	public void onApplicationEvent(StockPrepareUpdateEvent event) {
		Object object = event.getSource();
		String number = (object instanceof StockPrepare) ? ((StockPrepare) object)
				.getNumber() : (String) object;

		for (Vbap vbap : vbapRepository
				.findAll(new VbapRepository.FindByStockPrepare(number)))
			eventPublisher
					.publish(new StockPrepareWriteToSapEvent(vbap.getPk()));
	}
}
