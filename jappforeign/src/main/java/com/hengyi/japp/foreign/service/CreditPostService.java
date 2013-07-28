package com.hengyi.japp.foreign.service;

import com.hengyi.japp.foreign.domain.Operator;

public interface CreditPostService {
	void bindVbak(String number, String vbeln, Operator operator)
			throws Exception;

	void recieve(String number, Operator operator) throws Exception;

	void unrecieve(String number, Operator operator) throws Exception;
}
