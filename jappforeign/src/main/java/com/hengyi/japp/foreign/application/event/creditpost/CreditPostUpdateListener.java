package com.hengyi.japp.foreign.application.event.creditpost;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import com.hengyi.japp.foreign.application.event.EventPublisher;
import com.hengyi.japp.foreign.application.event.vbak.CreditPostWriteToSapEvent;
import com.hengyi.japp.foreign.domain.CreditPost;
import com.hengyi.japp.foreign.domain.Vbak;
import com.hengyi.japp.foreign.domain.repository.VbakRepository;
import com.hengyi.japp.foreign.service.CreditPostService;

@Named
public class CreditPostUpdateListener implements
		ApplicationListener<CreditPostUpdateEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected CreditPostService creditPostService;
	@Inject
	private VbakRepository vbakRepository;

	@Override
	public void onApplicationEvent(CreditPostUpdateEvent event) {
		Object object = event.getSource();
		String number = (object instanceof CreditPost) ? ((CreditPost) object)
				.getNumber() : (String) object;

		try {
			for (Vbak vbak : vbakRepository
					.findAll(new VbakRepository.FindByCrediPost(number)))
				eventPublisher.publish(new CreditPostWriteToSapEvent(vbak));
		} catch (Exception e) {
			log.error("信用证：{}，状态更新出错！", number);
		}
	}
}
