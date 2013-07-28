package com.hengyi.japp.foreign.application.event.vbak;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.foreign.domain.Vbak;
import com.hengyi.japp.foreign.service.SapServiceFacade;
import com.hengyi.japp.foreign.service.VbakService;

@Transactional
@Named
public class CreditPostWriteToSapListener implements
		ApplicationListener<CreditPostWriteToSapEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	private SapServiceFacade sapServiceFacade;
	@Inject
	private VbakService vbakService;

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
			sapServiceFacade.writeCreditPost(vbeln);
		} catch (Exception e) {
			log.error("销售单：{},信用证数据写回SAP失败！", vbeln);
		}
	}
}
