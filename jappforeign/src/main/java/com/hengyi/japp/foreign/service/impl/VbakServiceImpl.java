package com.hengyi.japp.foreign.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.sap.dto.SapVbapDTO;
import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.application.event.EventPublisher;
import com.hengyi.japp.foreign.application.event.SyncEventPublisher;
import com.hengyi.japp.foreign.domain.Vbak;
import com.hengyi.japp.foreign.domain.Vbap;
import com.hengyi.japp.foreign.domain.repository.CreditPostRepository;
import com.hengyi.japp.foreign.domain.repository.StockPrepareRepository;
import com.hengyi.japp.foreign.domain.repository.VbakRepository;
import com.hengyi.japp.foreign.domain.repository.VbapRepository;
import com.hengyi.japp.foreign.dto.ForeignSapVbakDTO;
import com.hengyi.japp.foreign.dto.VbakDTO;
import com.hengyi.japp.foreign.dto.VbakVbapDTO;
import com.hengyi.japp.foreign.dto.VbaksVbapsDTO;
import com.hengyi.japp.foreign.dto.VbapDTO;
import com.hengyi.japp.foreign.service.CacheService;
import com.hengyi.japp.foreign.service.SapServiceFacade;
import com.hengyi.japp.foreign.service.VbakService;

@Named
@Transactional
public class VbakServiceImpl implements VbakService {
	@Inject
	private CacheService cacheService;
	@Inject
	private SapServiceFacade sapServiceFacade;
	@Inject
	private VbakRepository vbakRepository;
	@Inject
	private VbapRepository vbapRepository;
	@Inject
	private CreditPostRepository creditPostRepository;
	@Inject
	private StockPrepareRepository stockPrepareRepository;
	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected SyncEventPublisher syncEventPublisher;
	@Inject
	private Mapper dozer;

	@Override
	public Vbak findOne(String vbeln) throws Exception {
		vbeln = sapServiceFacade.convertVbeln(vbeln);
		Vbak vbak = vbakRepository.findOne(vbeln);
		return vbak != null ? vbak : findOneFromSap(vbeln);
	}

	private Vbak findOneFromSap(String vbeln) throws Exception {
		ForeignSapVbakDTO foreignSapVbak = sapServiceFacade.findVbak(vbeln);
		Vbak vbak = new Vbak(foreignSapVbak.getSapVbak().getVbeln());
		for (SapVbapDTO sapVbap : foreignSapVbak.getSapVbaps()) {
			new Vbap(sapVbap.getPk(), vbak);
		}
		return vbakRepository.save(vbak);
	}

	@Override
	public Iterable<Vbak> findAll(Iterable<String> vbelns) throws Exception {
		return vbakRepository.findAll(vbelns);
	}

	@Override
	public VbakDTO findOneVbak(String vbeln) throws Exception {
		return dozer.map(findOne(vbeln), VbakDTO.class);
	}

	@Override
	public VbapDTO findOneVbap(VbapPK pk) throws Exception {
		return dozer.map(vbapRepository.findOne(pk), VbapDTO.class);
	}

	@Override
	public VbakVbapDTO findOneVbakVbap(String vbeln) throws Exception {
		VbakDTO vbak = findOneVbak(vbeln);
		List<VbapDTO> vbaps = Lists.newArrayList();
		for (Vbap vbap : findOne(vbeln).getVbaps())
			vbaps.add(dozer.map(vbap, VbapDTO.class));

		return new VbakVbapDTO(vbak, vbaps);
	}

	@Override
	public VbaksVbapsDTO findAllVbakVbap(Iterable<String> vbelns)
			throws Exception {
		List<VbakDTO> vbaks = Lists.newArrayList();
		List<VbapDTO> vbaps = Lists.newArrayList();

		for (Vbak vbak : vbakRepository.findAll(vbelns)) {
			vbaks.add(dozer.map(vbak, VbakDTO.class));

			for (Vbap vbap : vbak.getVbaps())
				vbaps.add(dozer.map(vbap, VbapDTO.class));
		}
		return new VbaksVbapsDTO(vbaks, vbaps);
	}

	@Override
	public void updateStatus(String vbeln) throws Exception {
		Vbak vbak = vbakRepository.findOne(vbeln);
		vbak.updateStatus();
		vbakRepository.save(vbak);
	}
}
