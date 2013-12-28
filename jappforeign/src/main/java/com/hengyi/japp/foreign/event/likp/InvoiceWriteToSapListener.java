package com.hengyi.japp.foreign.event.likp;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import com.hengyi.japp.foreign.domain.Likp;
import com.hengyi.japp.foreign.service.SapService;

@Named
public class InvoiceWriteToSapListener implements
		ApplicationListener<InvoiceWriteToSapEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	private SapService sapService;

	@Override
	public void onApplicationEvent(InvoiceWriteToSapEvent event) {
		Object object = event.getSource();
		String vbeln = (object instanceof Likp) ? ((Likp) object).getVbeln()
				: (String) object;
		try {
			sapService.writeCreditPost(vbeln);
		} catch (Exception e) {
			log.error("交货单：{},信用证数据写回SAP失败！", vbeln);
		}
	}
}
