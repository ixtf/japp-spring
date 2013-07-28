package com.hengyi.japp.foreign.service;

import java.util.List;

import com.hengyi.japp.foreign.domain.Likp;
import com.hengyi.japp.foreign.dto.LikpDTO;

public interface LikpService {
	Likp findOne(String vbeln) throws Exception;

	LikpDTO findOneLikp(String vbeln) throws Exception;

	List<LikpDTO> findAllLikp(Iterable<String> vbeln) throws Exception;
}
