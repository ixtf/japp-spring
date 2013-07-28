package com.hengyi.japp.foreign.service;

import com.hengyi.japp.foreign.domain.Operator;
import com.hengyi.japp.foreign.dto.common.InvoiceCommonDTO;

public interface InvoiceService {
	void bindLikp(String number, String vbeln, Operator operator)
			throws Exception;

	void update(InvoiceCommonDTO invoice, Operator operator) throws Exception;

	void insurance(String number, Operator operator) throws Exception;

	void uninsurance(String number, Operator operator) throws Exception;

	void recieve(String number, Operator operator) throws Exception;

	void unrecieve(String number, Operator operator) throws Exception;
}
