package com.hengyi.japp.foreign.event.vbak;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.foreign.domain.Vbak;
import com.hengyi.japp.foreign.service.SapService;

@Transactional
@Named
public class CreditPostWriteToSapListener implements
		ApplicationListener<CreditPostWriteToSapEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	private SapService sapService;

	@Override
	public void onApplicationEvent(CreditPostWriteToSapEvent event) {
		Object object = event.getSource();
		String vbeln = (object instanceof Vbak) ? ((Vbak) object).getVbeln()
				: (String) object;

		// updateStatus(vbeln);
		writeToSap(vbeln);
	}

	private void writeToSap(String vbeln) {
		try {
			sapService.writeCreditPost(vbeln);
		} catch (Exception e) {
			log.error("销售单：{},信用证数据写回SAP失败！", vbeln);
		}
	}
}
