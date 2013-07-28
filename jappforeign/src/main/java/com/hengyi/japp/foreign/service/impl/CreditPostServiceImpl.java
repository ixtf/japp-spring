package com.hengyi.japp.foreign.service.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.hengyi.japp.foreign.application.event.EventPublisher;
import com.hengyi.japp.foreign.application.event.creditpost.CreditPostUpdateEvent;
import com.hengyi.japp.foreign.application.event.vbak.CreditPostWriteToSapEvent;
import com.hengyi.japp.foreign.domain.CreditPost;
import com.hengyi.japp.foreign.domain.CreditPostRecieveInfo;
import com.hengyi.japp.foreign.domain.Operator;
import com.hengyi.japp.foreign.domain.Vbak;
import com.hengyi.japp.foreign.domain.repository.CreditPostRepository;
import com.hengyi.japp.foreign.service.CreditPostService;
import com.hengyi.japp.foreign.service.VbakService;

@Named
@Transactional
public class CreditPostServiceImpl implements CreditPostService {
	@Inject
	private EventPublisher eventPublisher;
	@Inject
	private VbakService vbakService;
	@Inject
	private CreditPostRepository creditPostRepository;

	@Override
	public void bindVbak(String number, String vbeln, Operator operator)
			throws Exception {
		Set<CreditPost> changedCrediPosts = Sets.newHashSet();
		Set<Vbak> changedVbaks = Sets.newHashSet();

		CreditPost creditPost = creditPostRepository.findOne(number);
		if (creditPost == null)
			creditPost = new CreditPost(number, operator);
		creditPost.setOperator(operator);
		Vbak vbak = vbakService.findOne(vbeln);

		if (creditPost.getVbaks().contains(vbak))
			return;

		for (CreditPost changedCrediPost : vbak.getCreditPosts()) {
			changedCrediPost.remove(vbak);
			changedCrediPosts.add(changedCrediPost);
		}
		for (Vbak changedVbak : creditPost.getVbaks()) {
			creditPost.remove(changedVbak);
			changedVbaks.add(changedVbak);
		}

		creditPost.add(vbak);
		changedCrediPosts.add(creditPost);
		changedVbaks.add(vbak);
		creditPostRepository.save(changedCrediPosts);
		creditPostRepository.flush();

		for (CreditPost changed : changedCrediPosts)
			eventPublisher.publish(new CreditPostUpdateEvent(changed));
		for (Vbak changed : changedVbaks)
			eventPublisher.publish(new CreditPostWriteToSapEvent(changed));
	}

	@Override
	public void recieve(String number, Operator operator) throws Exception {
		CreditPost creditPost = creditPostRepository.findOne(number);
		CreditPostRecieveInfo recieveInfo = creditPost.getRecieveInfo();
		if (recieveInfo == null)
			recieveInfo = new CreditPostRecieveInfo(creditPost, operator);
		recieveInfo.setOperator(operator);
		recieveInfo.setRecieve(true);
		creditPostRepository.save(creditPost);
		creditPostRepository.flush();

		eventPublisher.publish(new CreditPostUpdateEvent(creditPost));
		for (Vbak changed : creditPost.getVbaks())
			eventPublisher.publish(new CreditPostWriteToSapEvent(changed));
	}

	@Override
	public void unrecieve(String number, Operator operator) throws Exception {
		CreditPost creditPost = creditPostRepository.findOne(number);
		CreditPostRecieveInfo recieveInfo = creditPost.getRecieveInfo();
		recieveInfo.setOperator(operator);
		recieveInfo.setRecieve(false);
		creditPostRepository.save(creditPost);
		creditPostRepository.flush();

		eventPublisher.publish(new CreditPostUpdateEvent(creditPost));
		for (Vbak changed : creditPost.getVbaks())
			eventPublisher.publish(new CreditPostWriteToSapEvent(changed));
	}
}
