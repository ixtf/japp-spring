package com.hengyi.japp.common.service;

import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.common.command.BindCorporationUpdateCommand;
import com.hengyi.japp.common.command.CorporationBindCommand;
import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.domain.node.Corporation;
import com.hengyi.japp.common.domain.node.User;
import com.hengyi.japp.common.domain.node.bind.BindCorporation;

@Transactional
public interface CorporationService {
	Corporation save(Corporation corporation);

	BindCorporation save(BindCorporation bindCorporation);

	Corporation findOne(String uuid);

	<T extends BindCorporation> T findOneBindCorporation(
			BindCorporationType bindCorporationType, String id);

	BindCorporation bindCorporation(CorporationBindCommand command)
			throws Exception;

	Iterable<Corporation> findAllByUser(User user);

	Iterable<BindCorporation> queryAllBindCorporationByName(String query);

	BindCorporation save(BindCorporationUpdateCommand command);
}
