package com.hengyi.japp.foreign.application.event.invoice;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;

import com.hengyi.japp.foreign.application.event.EventPublisher;
import com.hengyi.japp.foreign.application.event.likp.InvoiceWriteToSapEvent;
import com.hengyi.japp.foreign.domain.Invoice;
import com.hengyi.japp.foreign.domain.Likp;
import com.hengyi.japp.foreign.domain.repository.LikpRepository;

@Named
public class InvoiceUpdateEventListener implements
		ApplicationListener<InvoiceUpdateEvent> {
	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	private LikpRepository likpRepository;

	@Override
	public void onApplicationEvent(InvoiceUpdateEvent event) {
		Object object = event.getSource();
		String number = (object instanceof Invoice) ? ((Invoice) object)
				.getNumber() : (String) object;

		for (Likp likp : likpRepository
				.findAll(new LikpRepository.FindByInvoice(number)))
			eventPublisher.publish(new InvoiceWriteToSapEvent(likp));
	}
}
