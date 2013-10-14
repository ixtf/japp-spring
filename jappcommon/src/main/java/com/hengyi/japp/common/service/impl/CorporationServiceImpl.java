package com.hengyi.japp.common.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.ImmutableSet;
import com.hengyi.japp.common.CommonConstant;
import com.hengyi.japp.common.Constant;
import com.hengyi.japp.common.command.BindCorporationUpdateCommand;
import com.hengyi.japp.common.command.CorporationBindCommand;
import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.domain.node.Corporation;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.BindCorporation;
import com.hengyi.japp.common.domain.node.bind.BindUser;
import com.hengyi.japp.common.domain.node.bind.corporation.AbstractBindCorporation;
import com.hengyi.japp.common.domain.repository.CorporationRepository;
import com.hengyi.japp.common.domain.repository.bind.BindCorporationRepository;
import com.hengyi.japp.common.service.CorporationService;

@Named
@SuppressWarnings("unchecked")
public class CorporationServiceImpl implements CorporationService {
	@Resource
	private Neo4jOperations template;
	@Resource
	private CorporationRepository corporationRepository;
	@Resource
	private BindCorporationRepository bindCorporationRepository;

	@Override
	public Corporation save(Corporation corporation) {
		Corporation oldCorporation = findOne(corporation.getUuid());
		if (oldCorporation == null)
			return corporationRepository.save(corporation);
		oldCorporation.setShortName(corporation.getShortName());
		oldCorporation.setFullName(corporation.getFullName());
		return corporationRepository.save(oldCorporation);
	}

	@Override
	public BindCorporation save(BindCorporation bindCorporation) {
		return bindCorporationRepository.save(bindCorporation);
	}

	@Override
	public Corporation findOne(String uuid) {
		return corporationRepository.findByPropertyValue(
				Corporation.class.getSimpleName(), "uuid", uuid);
	}

	@Override
	public <T extends BindCorporation> T findOneBindCorporation(
			BindCorporationType bindCorporationType, String id) {
		BindCorporation bindCorporation = bindCorporationRepository
				.findByPropertyValue(
						Constant.getBindCorporationTypeReferClass()
								.get(bindCorporationType).getSimpleName(),
						"id", id);
		return bindCorporation == null ? null : (T) bindCorporation;
	}

	@Override
	public BindCorporation bindCorporation(CorporationBindCommand command)
			throws Exception {
		Corporation corporation = findOne(command.getUuid());
		if (corporation == null)
			throw new Exception(CommonConstant.ErrorCode.CORPORATION_NOT_EXIST
					+ command.getUuid());
		AbstractBindCorporation bindCorporation = findOneBindCorporation(
				command.getBindCorporationType(), command.getId());
		if (bindCorporation == null)
			bindCorporation = new BindCorporation.Builder()
					.bindCorporationType(command.getBindCorporationType())
					.id(command.getId()).name(corporation.getName()).build();
		bindCorporation.setCorporation(corporation);
		return save(bindCorporation);
	}

	@Override
	public Iterable<Corporation> findAllByUser(User user) {
		ImmutableSet.Builder<Corporation> builder = ImmutableSet.builder();
		for (Corporation corporation : user.getCorporations()) {
			builder.add(corporation);
		}
		for (BindUser bindUser : user.getBindUsers()) {
			builder.add(getCorporation(bindUser));
		}
		return builder.build();
	}

	private Corporation getCorporation(BindUser bindUser) {
		return null;
	}

	@Override
	public Iterable<BindCorporation> queryAllBindCorporationByName(String query) {
		return bindCorporationRepository.findAllByQuery(
				BindCorporation.nameSearch, "name", query);
	}

	@Override
	public BindCorporation save(BindCorporationUpdateCommand command) {
		AbstractBindCorporation bindCorporation = new BindCorporation.Builder()
				.bindCorporationType(command.getBindCorporationType())
				.id(command.getId()).name(command.getName()).build();
		BindCorporation oldBindCorporation = findOneBindCorporation(
				command.getBindCorporationType(), command.getId());
		if (oldBindCorporation != null)
			bindCorporation.setCorporation(oldBindCorporation.getCorporation());
		return save(bindCorporation);
	}
}
