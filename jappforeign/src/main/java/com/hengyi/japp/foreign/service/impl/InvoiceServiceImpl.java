package com.hengyi.japp.foreign.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.foreign.Constant;
import com.hengyi.japp.foreign.application.event.EventPublisher;
import com.hengyi.japp.foreign.application.event.invoice.InvoiceUpdateEvent;
import com.hengyi.japp.foreign.application.event.likp.InvoiceWriteToSapEvent;
import com.hengyi.japp.foreign.domain.Invoice;
import com.hengyi.japp.foreign.domain.InvoiceInsuranceInfo;
import com.hengyi.japp.foreign.domain.InvoiceRecieveInfo;
import com.hengyi.japp.foreign.domain.Likp;
import com.hengyi.japp.foreign.domain.Operator;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.repository.InvoiceRepository;
import com.hengyi.japp.foreign.domain.repository.LikpRepository;
import com.hengyi.japp.foreign.dto.common.InvoiceCommonDTO;
import com.hengyi.japp.foreign.service.InvoiceService;
import com.hengyi.japp.foreign.service.LikpService;

@Named
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
	@Inject
	private EventPublisher eventPublisher;
	@Inject
	private LikpService likpService;
	@Inject
	private InvoiceRepository invoiceRepository;
	@Inject
	private LikpRepository likpRepository;
	@Inject
	private Mapper dozer;

	@Override
	public void bindLikp(String number, String vbeln, Operator operator)
			throws Exception {
		Invoice invoice = invoiceRepository.findOne(number);
		if (invoice == null)
			invoice = new Invoice(number, operator);
		invoice.setOperator(operator);
		Likp likp = likpService.findOne(vbeln);
		invoice.bindLikp(likp);
		invoiceRepository.save(invoice);
		invoiceRepository.flush();
		eventPublisher.publish(new InvoiceWriteToSapEvent(vbeln));
	}

	@Override
	public void update(InvoiceCommonDTO dto, Operator operator)
			throws Exception {
		Invoice invoice = invoiceRepository.findOne(dto.getNumber());
		if (!invoice.getStatus().equals(Status.INIT))
			throw new Exception(Constant.ErrorCode.MODEIFY_NOT_INIT_INVOICE);
		dozer.map(dto, invoice);
		invoice.setOperator(operator);
		invoiceRepository.save(invoice);
		invoiceRepository.flush();
		eventPublisher.publish(new InvoiceUpdateEvent(invoice));
	}

	@Override
	public void insurance(String number, Operator operator) throws Exception {
		Invoice invoice = invoiceRepository.findOne(number);
		InvoiceInsuranceInfo insuranceInfo = invoice.getInsuranceInfo();
		if (insuranceInfo == null)
			insuranceInfo = new InvoiceInsuranceInfo(invoice, operator);
		insuranceInfo.setInsurance(true);
		insuranceInfo.setOperator(operator);
		invoiceRepository.save(invoice);
		invoiceRepository.flush();
		eventPublisher.publish(new InvoiceUpdateEvent(invoice));
	}

	@Override
	public void uninsurance(String number, Operator operator) throws Exception {
		Invoice invoice = invoiceRepository.findOne(number);
		InvoiceInsuranceInfo insuranceInfo = invoice.getInsuranceInfo();
		insuranceInfo.setInsurance(false);
		insuranceInfo.setOperator(operator);
		invoiceRepository.save(invoice);
		invoiceRepository.flush();
		eventPublisher.publish(new InvoiceUpdateEvent(invoice));
	}

	@Override
	public void recieve(String number, Operator operator) throws Exception {
		Invoice invoice = invoiceRepository.findOne(number);
		InvoiceRecieveInfo recieveInfo = invoice.getRecieveInfo();
		if (recieveInfo == null)
			recieveInfo = new InvoiceRecieveInfo(invoice, operator);
		recieveInfo.setRecieve(true);
		recieveInfo.setOperator(operator);
		invoiceRepository.save(invoice);
		invoiceRepository.flush();
		eventPublisher.publish(new InvoiceUpdateEvent(invoice));
	}

	@Override
	public void unrecieve(String number, Operator operator) throws Exception {
		Invoice invoice = invoiceRepository.findOne(number);
		InvoiceRecieveInfo recieveInfo = invoice.getRecieveInfo();
		recieveInfo.setRecieve(false);
		recieveInfo.setOperator(operator);
		invoiceRepository.save(invoice);
		invoiceRepository.flush();
		eventPublisher.publish(new InvoiceUpdateEvent(invoice));
	}

}
