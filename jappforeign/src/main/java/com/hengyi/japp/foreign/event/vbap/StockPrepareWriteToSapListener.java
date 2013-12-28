package com.hengyi.japp.foreign.event.vbap;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.domain.Vbap;
import com.hengyi.japp.foreign.service.SapService;

@Transactional
@Named
public class StockPrepareWriteToSapListener implements
		ApplicationListener<StockPrepareWriteToSapEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	private SapService sapService;

	@Override
	public void onApplicationEvent(StockPrepareWriteToSapEvent event) {
		Object object = event.getSource();
		VbapPK pk = (object instanceof Vbap) ? ((Vbap) object).getPk()
				: (VbapPK) event.getSource();
		writeToSap(pk);
	}

	private void writeToSap(VbapPK pk) {
		try {
			sapService.writeCreditPost(pk.getVbeln());
			sapService.writeStockPrepare(pk);
		} catch (Exception e) {
			log.error("销售单：{}，行项目：{}，备货单数据写回SAP失败！", pk.getVbeln(),
					pk.getPosnr());
			log.error("", e);
		}
	}
}
