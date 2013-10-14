package com.hengyi.japp.crm.service.impl;

import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.service.impl.CommonUrlServiceImpl;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.repository.IndicatorRepository;
import com.hengyi.japp.crm.event.EventPublisher;
import com.hengyi.japp.crm.event.SyncEventPublisher;
import com.hengyi.japp.crm.event.indicator.IndicatorUpdateEvent;
import com.hengyi.japp.crm.exception.ReUseIndicatorValueException;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.IndicatorService;

public abstract class IndicatorServiceImpl<T extends Indicator> extends
		CommonUrlServiceImpl<Long> implements IndicatorService<T> {
	@Resource
	protected Neo4jOperations template;
	@Resource
	protected IndicatorRepository indicatorRepository;

	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected SyncEventPublisher syncEventPublisher;

	@Override
	public void save(T indicator,
			Iterable<IndicatorValueScore> indicatorValueScores)
			throws Exception {
		checkSave(indicator, indicatorValueScores);
		boolean isUpdate = indicator.getNodeId() != null;
		indicator.setIndicatorValueScores(indicatorValueScores);
		indicatorRepository.save(indicator);
		for (IndicatorValueScore indicatorValueScore : indicatorValueScores)
			template.save(indicatorValueScore);
		if (isUpdate)
			eventPublisher.publish(new IndicatorUpdateEvent(indicator));
	}

	private void checkSave(T indicator,
			Iterable<IndicatorValueScore> indicatorValueScores)
			throws Exception {
		Set<IndicatorValue> reUseIndicatorValues = Sets.newHashSet();
		for (IndicatorValueScore indicatorValueScore : indicatorValueScores) {
			IndicatorValue indicatorValue = indicatorValueScore.getEnd();
			if (indicatorRepository.findAllByIndicatorValue(indicatorValue)
					.iterator().hasNext())
				reUseIndicatorValues.add(indicatorValue);
		}
		if (!reUseIndicatorValues.isEmpty())
			throw new ReUseIndicatorValueException(reUseIndicatorValues);
	}

	@Override
	public void delete(T indicator) throws Exception {
		indicatorRepository.delete(indicator);
	}

	@Override
	public String getNewPath() {
		return getCrmService().getNewPath() + "/indicator";
	}

	protected abstract CrmService<?> getCrmService();
}
