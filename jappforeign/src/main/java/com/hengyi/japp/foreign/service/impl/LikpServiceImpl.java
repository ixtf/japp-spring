package com.hengyi.japp.foreign.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.sap.dto.SapLipsDTO;
import com.hengyi.japp.foreign.domain.Likp;
import com.hengyi.japp.foreign.domain.Lips;
import com.hengyi.japp.foreign.domain.repository.LikpRepository;
import com.hengyi.japp.foreign.dto.ForeignSapLikpDTO;
import com.hengyi.japp.foreign.dto.LikpDTO;
import com.hengyi.japp.foreign.service.LikpService;
import com.hengyi.japp.foreign.service.SapService;

@Named
@Transactional
public class LikpServiceImpl implements LikpService {
	@Inject
	private SapService sapService;
	@Inject
	private LikpRepository likpRepository;
	@Inject
	private Mapper dozer;

	@Override
	public Likp findOne(String vbeln) throws Exception {
		vbeln = sapService.convertVbeln(vbeln);
		Likp likp = likpRepository.findOne(vbeln);
		return likp != null ? likp : findOneFromSap(vbeln);
	}

	private Likp findOneFromSap(String vbeln) throws Exception {
		ForeignSapLikpDTO foreignSapLikp = sapService.findLikp(vbeln);
		Likp likp = new Likp(foreignSapLikp.getSapLikp().getVbeln());
		for (SapLipsDTO sapLips : foreignSapLikp.getSapLipss()) {
			Lips lips = dozer.map(sapLips, Lips.class);
			lips.setLikp(likp);
		}
		return likpRepository.save(likp);
	}

	@Override
	public LikpDTO findOneLikp(String vbeln) throws Exception {
		return dozer.map(findOne(vbeln), LikpDTO.class);
	}

	@Override
	public List<LikpDTO> findAllLikp(Iterable<String> vbelns) throws Exception {
		List<LikpDTO> result = Lists.newArrayList();
		for (Likp likp : likpRepository.findAll(vbelns))
			result.add(dozer.map(likp, LikpDTO.class));
		return result;
	}

}
