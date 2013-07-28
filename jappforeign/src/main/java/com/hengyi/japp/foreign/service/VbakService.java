package com.hengyi.japp.foreign.service;

import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.domain.Vbak;
import com.hengyi.japp.foreign.dto.VbakDTO;
import com.hengyi.japp.foreign.dto.VbakVbapDTO;
import com.hengyi.japp.foreign.dto.VbaksVbapsDTO;
import com.hengyi.japp.foreign.dto.VbapDTO;

public interface VbakService {
	Vbak findOne(String vbeln) throws Exception;

	Iterable<Vbak> findAll(Iterable<String> vbelns) throws Exception;

	VbakDTO findOneVbak(String vbeln) throws Exception;

	VbapDTO findOneVbap(VbapPK pk) throws Exception;

	VbakVbapDTO findOneVbakVbap(String vbeln) throws Exception;

	VbaksVbapsDTO findAllVbakVbap(Iterable<String> vbelns) throws Exception;

	void updateStatus(String vbeln) throws Exception;
}
